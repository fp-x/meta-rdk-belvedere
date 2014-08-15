
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://01-dbus-ccsp-apis.patch"

do_install_append_class-target () {
    install -m 644 ${WORKDIR}/${PN}-${PV}/dbus/dbus-mainloop.h ${D}/usr/include/dbus-1.0/dbus
}

