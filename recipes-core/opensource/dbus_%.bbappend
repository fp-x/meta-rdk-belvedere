
FILESEXTRAPATHS_prepend := "${THISDIR}:"

SRC_URI += "file://01-compile_in_dbus_mainloop.patch"

do_install_append_class-target () {
    install -m 644 ${WORKDIR}/${PN}-${PV}/dbus/dbus-mainloop.h ${D}/usr/include/dbus-1.0/dbus
}

