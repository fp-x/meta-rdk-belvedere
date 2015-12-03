SUMMARY = "CCSP WifiAgent component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspWifiAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library virtual/ccsp-hal"
require ccsp_common.inc
SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspWifiAgent${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspWifiAgent"

SRCREV_CcspWifiAgent = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

CFLAGS_append = " \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    -I${STAGING_INCDIR}/ccsp \
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
    ${libdir}/libwifi.so* \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/wifi/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
