SUMMARY = "CCSP Reboot Manager utility."
HOMEPAGE = "http://github.com/belvedere-yocto/RebootManager"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"
require recipes-core/ccsp/ccsp_common.inc
SRC_URI = "${RDKB_CCSP_ROOT_GIT}/RebootManager${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=RebootManager"

SRCREV_RebootManager = "${AUTOREV}"
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

do_install_pc_sources () {
    echo "=================== running do_install_pc_sources..."
    install -m 644 ${S}/source-pc/CcspRmHal.c -t ${S}/source/RmSsp
}

do_install_pc_config () {
    echo "=================== running do_install_pc_config..."
    install -m 644 ${S}/config/RebootManager_pc.xml ${D}/usr/ccsp/rm/RebootManager.xml 
}

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -m 644 ${S}/source-arm/CcspRmHal.c -t ${S}/source/RmSsp
}

do_install_arm_config () {
    echo "=================== running do_install_arm_config..."
    install -m 644 ${S}/config/RebootManager_arm.xml ${D}/usr/ccsp/rm/RebootManager.xml 
}

do_configure_prepend_qemux86 () {
    do_install_pc_sources
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
    install -d ${D}/usr/ccsp/rm
    install -m 777 ${D}/usr/bin/CcspRmSsp -t ${D}/usr/ccsp/rm
}

do_install_append_qemux86 () {
    # Config files and scripts
    do_install_pc_config
}

do_install_append_qemuarm () {
    # Config files and scripts
    do_install_arm_config
}

do_install_append_raspberrypi () {
    # Config files and scripts
    do_install_arm_config
}

do_install_append_armeb () {
    # Config files and scripts
    do_install_arm_config
}

do_install_append_puma6 () {
    # Config files and scripts
    do_install_arm_config
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/rm/CcspRmSsp \
    ${prefix}/ccsp/rm/RebootManager.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/rm/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
