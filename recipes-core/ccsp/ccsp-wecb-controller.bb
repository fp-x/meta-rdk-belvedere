SUMMARY = "CCSP WECB Controller."
HOMEPAGE = "http://github.com/belvedere-yocto/CcspWecbController"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library utopia libupnp curl"

SRC_URI = "\
git://github.com/belvedere-yocto/CcspWecbController.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
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

do_configure_prepend_puma6 () {
    do_install_arm_sources
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -m 777 ${D}/usr/bin/CcspWecbController -t ${D}/usr/ccsp
    #install -m 777 ${D}/usr/bin/wecb_master -t ${D}/usr/ccsp
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${bindir}/CcspWecbController \
    ${prefix}/ccsp/CcspWecbController \
    #${prefix}/ccsp/wecb_master \
    ${libdir}/libwecb.so* \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
