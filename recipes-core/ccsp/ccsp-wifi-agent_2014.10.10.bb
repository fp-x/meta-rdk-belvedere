SUMMARY = "CCSP WifiAgent component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspWifiAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library hal"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspWifiAgent.git;protocol=git;branch=daisy;rev=daisy \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    "

LDFLAGS_append = " \
    -ldbus-1 \
    "

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/wifi
    install -m 777 ${D}/usr/bin/CcspWifiSsp -t ${D}/usr/ccsp/wifi
}

FILES_${PN} = " \
    ${bindir}/CcspWifiSsp \
    ${prefix}/ccsp/wifi/CcspWifiSsp \
    ${libdir}/libwifi.so.* \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/wifi/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
