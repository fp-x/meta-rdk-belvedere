ifconfig wan0:0 192.168.101.1
dbus-daemon --config-file=./basic.conf --fork
# dbus-monitor --address tcp:host=192.168.101.1,port=12368 &
