SUMMARY = "CCSP PsmSsp component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspPsm"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library dbus"

require ccsp_common.inc


SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspPsm${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspPsm"

SRCREV_CcspPsm = "${AUTOREV}"
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
    echo "=================== running do_install_arm_sources..."
    install -m 644 ${S}/source-pc/ssp_HAL_apis.c ${S}/source/Ssp/psm_hal_apis.c
}

do_install_pc_config () {
    echo "=================== running do_install_arm_config..."
    install -m 644 ${S}/config/bbhm_def_cfg_pc.xml ${D}/usr/ccsp/config/bbhm_def_cfg.xml
}

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -m 644 ${S}/source-arm/psm_hal_apis.c -t ${S}/source/Ssp
}

do_install_arm_config () {
    echo "=================== running do_install_arm_config..."
    install -m 644 ${S}/config/bbhm_def_cfg_arm.xml ${D}/usr/ccsp/config/bbhm_def_cfg.xml
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

do_configure_prepend_puma6 () {
    do_install_arm_sources
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/config
    install -m 777 ${D}/usr/bin/PsmSsp -t ${D}/usr/ccsp
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

FILES_${PN} = " \
    ${prefix}/ccsp/PsmSsp \
    ${prefix}/ccsp/config/bbhm_def_cfg.xml \
    /usr/bin/ \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
