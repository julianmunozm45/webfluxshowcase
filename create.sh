#!/bin/bash
port=${2:-8080}
# shellcheck disable=SC2034
NAMES=$(curl https://namey.muffinlabs.com/name.json?count=1\&with_surname=false\&frequency=common)
nameStr=$(echo "$NAMES" | cut -d "[" -f2 | cut -d "]" -f1)
name=$(echo "$nameStr" | cut -d "\"" -f2 | cut -d "\"" -f1)
email="${name,,}@kmail.com"
echo "$email"
curl -H "content-type: application/json" -d "{\"email\":\"$email\"}" http://localhost:"${port}"/profiles