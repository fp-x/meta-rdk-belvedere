
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

EXTRA_OECONF = "--disable-tests \
                --disable-xml-docs \
                --disable-doxygen-docs \
                --disable-libaudit \
                --disable-checks \
                --with-xml=expat \
                --disable-systemd"
