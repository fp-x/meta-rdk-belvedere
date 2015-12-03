SUMMARY = "CCSP WECB Controller."
HOMEPAGE = "http://github.com/belvedere-yocto/CcspWecbController"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library utopia libupnp curl libxml2"
require ccsp_common.inc
SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspWecbController${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspWecbController"

SRCREV_CcspWecbController = "${AUTOREV}"
SRCREV_FORMAT = "CcspWecbController"

PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

CFLAGS += " \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    -I${STAGING_INCDIR}/ccsp \
    "

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -d ${WORKDIR}/git/source/TR-181/board_sbapi
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_wifi_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_comcast_com_gre_apis.h -t ${WORKDIR}/git/source/TR-181/board_sbapi
}

do_configure_prepend_qemuarm () {
    do_install_arm_sources
}

do_configure_prepend_raspberrypi () {
    do_install_arm_sources
}

do_configure_prepend_armeb () {
    do_install_arm_sources
}

do_configure_prepend_puma6 () {
    do_install_arm_sources
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -d ${D}/usr/ccsp/wecb
    install -m 777 ${D}/usr/bin/CcspWecbController -t ${D}/usr/ccsp
    #install -m 777 ${D}/usr/bin/wecb_master -t ${D}/usr/ccsp
}

do_install_append_armeb () {
    # Config files and scripts
    install -m 644 ${S}/config/CcspWecb.cfg ${D}/usr/ccsp/wecb/CcspWecb.cfg 
    install -m 644 ${S}/config/CcspWecbController_dm.xml ${D}/usr/ccsp/wecb/CcspWecbController_dm.xml
    install -m 644 ${S}/config/CcspWecbLib.cfg ${D}/usr/ccsp/wecb/CcspWecbLib.cfg 
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    /usr/ccsp/CcspWecbController \
    /usr/ccsp/* \
    /usr/ccsp/wecb/* \
    /usr/ccsp/wecb/CcspWecb.cfg \
    /usr/ccsp/wecb/CcspWecbController_dm.xml \
    /usr/ccsp/wecb/CcspWecbLib.cfg  \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
