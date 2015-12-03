SUMMARY = "CCSP Tr069Pa component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspPsm"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library dbus virtual/ccsp-hal"
DEPENDS_append_puma6 = " util-linux"

require ccsp_common.inc

SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspTr069Pa${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspTr069Pa"

SRCREV_CcspTr069Pa = "${AUTOREV}"
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
    install -m 644 ${WORKDIR}/git/source-pc/ccsp_tr069_pa_custom_apis.c -t ${WORKDIR}/git/source/Ssp
}

do_install_pc_config () {
    echo "=================== running do_install_pc_config..."
    install -m 644 ${WORKDIR}/git/config/ccsp_tr069_pa_certificate_cfg_pc.xml ${D}/usr/ccsp/tr069pa/ccsp_tr069_pa_certificate_cfg.xml
    install -m 644 ${WORKDIR}/git/config/ccsp_tr069_pa_cfg_pc.xml ${D}/usr/ccsp/tr069pa/ccsp_tr069_pa_cfg.xml
    install -m 644 ${WORKDIR}/git/config/ccsp_tr069_pa_mapper_pc.xml ${D}/usr/ccsp/tr069pa/ccsp_tr069_pa_mapper.xml
    install -m 644 ${WORKDIR}/git/config/sdm_arm.xml ${D}/usr/ccsp/tr069pa/sdm.xml
}

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -m 644 ${WORKDIR}/git/source-arm/ccsp_tr069_pa_custom_apis.c -t ${WORKDIR}/git/source/Ssp
}

do_install_arm_config () {
    echo "=================== running do_install_arm_config..."
    install -m 644 ${WORKDIR}/git/config/ccsp_tr069_pa_certificate_cfg_arm.xml ${D}/usr/ccsp/tr069pa/ccsp_tr069_pa_certificate_cfg.xml
    install -m 644 ${WORKDIR}/git/config/ccsp_tr069_pa_cfg_arm.xml ${D}/usr/ccsp/tr069pa/ccsp_tr069_pa_cfg.xml
    install -m 644 ${WORKDIR}/git/config/ccsp_tr069_pa_mapper_arm.xml ${D}/usr/ccsp/tr069pa/ccsp_tr069_pa_mapper.xml
    install -m 644 ${WORKDIR}/git/config/sdm_arm.xml ${D}/usr/ccsp/tr069pa/sdm.xml
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
    install -d ${D}/usr/ccsp/tr069pa
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

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/tr069pa/ccsp_tr069_pa_certificate_cfg.xml \
    ${prefix}/ccsp/tr069pa/ccsp_tr069_pa_cfg.xml \
    ${prefix}/ccsp/tr069pa/ccsp_tr069_pa_mapper.xml \
    ${prefix}/ccsp/tr069pa/sdm.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/tr069pa/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
