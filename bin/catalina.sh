#! /bin/sh
SERVER_NAME=smgateway-1.0.0.jar
SERVER_DIR=$(pwd)
echo $SERVER_DIR
cd $SERVER_DIR
# start service
start(){
        java -Xms128m -Xmx1024m -jar $SERVER_DIR/$SERVER_NAME 5 > start.log &
        tail -f start.log
}
# stop service
stop(){
        ps -ef|grep $SERVER_NAME|awk '{print $2}'|while read pid
        do
           kill -9 $pid
        done
}

case "$1" in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  stop
  start
  ;;
*)
  printf 'Usage: %s {start|stop|restart}\n' "$prog"
  exit 1
  ;;
esac