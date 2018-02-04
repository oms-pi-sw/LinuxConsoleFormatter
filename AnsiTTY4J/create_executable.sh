#!/bin/bash

code='#!/bin/sh
MYSELF=`which "$0" 2>/dev/null`
[ $? -gt 0 -a -f "$0" ] && MYSELF="./$0"
java=java
if test -n "$JAVA_HOME"; then
    java="$JAVA_HOME/bin/java"
fi
exec "$java" $java_args -jar $MYSELF "$@"
exit 1'

cd "dist"

name="$1"
if [ "$name" = "" ]; then
  name="AnsiTTY4J-pack.jar"
fi

echo "$code" >stub.sh
echo -e "\e[33m$code\e[m"
cat stub.sh "$name" >AnsiTTY4J.run && chmod +x AnsiTTY4J.run
rm stub.sh

echo "DONE."

exit 0
