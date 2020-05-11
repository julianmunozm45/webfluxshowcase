#!/bin/bash
port=${2:-8080}
email=$1
NAMES=$(curl https://namey.muffinlabs.com/name.json?count=1&with_surname=true&frequency=common)
nameStr=$(echo $NAMES | cut -d "[" -f2 | cut -d "]" -f1)
name=$(echo $nameStr | cut -d "\"" -f2 | cut -d "\"" -f1)
echo $name
curl -H "content-type: application/json" -d "{\"email\":\"$name\"}" http://localhost:${port}/profiles