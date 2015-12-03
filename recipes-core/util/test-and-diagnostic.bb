SUMMARY = "CCSP test and diagnostice utilities."
HOMEPAGE = "http://github.com/belvedere-yocto/TestAndDiagnostic"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"
require recipes-core/ccsp/ccsp_common.inc
SRC_URI = "${RDKB_CCSP_ROOT_GIT}/TestAndDiagnostic${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=TestAndDiagnostic"

SRCREV_TestAndDiagnostic = "${AUTOREV}"
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

do_install_pc_config () {
    install -m 644 ${S}/config/TestAndDiagnostic_pc.XML ${D}/usr/ccsp/tad/TestAndDiagnostic.XML
}

do_install_arm_config () {
    install -m 644 ${S}/config/TestAndDiagnostic_arm.XML ${D}/usr/ccsp/tad/TestAndDiagnostic.XML
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/tad
    install -m 777 ${D}/usr/bin/CcspTandDSsp ${D}/usr/ccsp/tad
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
    ${libdir}/libdiagnostic.so.* \
    ${libdir}/libdmltad.so.* \
    ${bindir}/CcspTandDSsp \
    ${prefix}/ccsp/tad/CcspTandDSsp \
    ${prefix}/ccsp/tad/TestAndDiagnostic.XML \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/tad/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
