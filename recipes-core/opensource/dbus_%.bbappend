
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://01-dbus-ccsp-apis-${PV}.patch"

EXTRA_OECONF = "--disable-tests \
                --disable-xml-docs \
                --disable-doxygen-docs \
                --disable-libaudit \
                --disable-checks \
                --with-xml=expat \
                --disable-systemd"
