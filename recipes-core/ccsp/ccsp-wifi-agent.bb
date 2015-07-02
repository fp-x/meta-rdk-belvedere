SUMMARY = "CCSP WifiAgent component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspWifiAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library hal"

SRC_URI = "\
    git://github.com/belvedere-yocto/CcspWifiAgent.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

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
    echo "CcspWifiAgent do_install_append."
    echo "     D = ${D}"
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
