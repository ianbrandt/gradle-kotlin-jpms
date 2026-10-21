#!/usr/bin/env bash
# Runs each scenario's tasks and compares the result with its expected file.
#
# Usage: ./check-scenarios.sh [scenario...]
#
# The build cache is off because the #17271 scenarios fail when compileKotlin
# is restored from it. They still fail intermittently with the cache off, so a
# single FAIL on one of them is worth a rerun before it is reported.
set -u

root=$(cd "$(dirname "$0")" && pwd)
logs="$root/build/check-scenarios"
mkdir -p "$logs"

if [ $# -gt 0 ]; then
	scenarios=("$@")
else
	scenarios=()
	for dir in "$root"/scenarios/*/; do
		scenarios+=("$(basename "$dir")")
	done
fi

# Succeeds when the outcome matches the expected one: "pass", or "fail:"
# followed by text the failure output must contain.
matches() {
	local expected=$1 actual=$2 log=$3
	case $expected in
		pass) [ "$actual" = pass ] ;;
		fail:*) [ "$actual" = fail ] && grep -qF -- "${expected#fail:}" "$log" ;;
		*) return 1 ;;
	esac
}

status=0
for name in "${scenarios[@]}"; do
	dir="$root/scenarios/$name"
	# Gradle reads gradle.properties only from the build's root directory, so
	# each scenario has a copy of the shared one.
	if ! cmp -s "$root/gradle.properties" "$dir/gradle.properties"; then
		echo "FAIL  $name: gradle.properties differs from $root/gradle.properties"
		status=1
	fi
	while IFS='=' read -r task expected; do
		log="$logs/$name-$task.log"
		if "$root/gradlew" -p "$dir" --console=plain --no-build-cache \
			clean "$task" > "$log" 2>&1 < /dev/null; then
			actual=pass
		else
			actual=fail
		fi
		if matches "$expected" "$actual" "$log"; then
			echo "ok    $name $task"
		else
			echo "FAIL  $name $task: expected $expected, got $actual ($log)"
			status=1
		fi
	done < "$dir/expected"
done
exit $status
