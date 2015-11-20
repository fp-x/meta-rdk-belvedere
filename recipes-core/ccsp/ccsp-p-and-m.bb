SUMMARY = "CCSP PandMSsp component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspPandM"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library utopia ccsp-lm-lite"

SRC_URI = "\
    git://github.com/belvedere-yocto/CcspPandM.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    -I=$(includedir)/utapi \
    -I=$(includedir)/utctx \
    -I=$(includedir)/ulog \
    "

CFLAGS_append_qemux86 += "-D_COSA_SIM_"
CFLAGS_append_puma6 += "-D_COSA_INTEL_USG_ARM_"

LDFLAGS_append = " \
    -ldbus-1 \
    "

do_install_pc_sources () {
    echo "=================== running do_install_pc_sources..."
    install -m 644 ${WORKDIR}/git/Makefile.am.pc ${WORKDIR}/git/Makefile.am
}

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -m 644 ${WORKDIR}/git/Makefile.am.arm ${WORKDIR}/git/Makefile.am
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
    install -d ${D}/usr/ccsp/pam
    install -m 777 ${D}/usr/bin/CcspPandMSsp -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-pc/COSAXcalibur.XML -t ${D}/usr/ccsp/pam
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-pc/CcspDmLib.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
    install -m 644 ${WORKDIR}/git/config-pc/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-pc/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspDmLib.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-arm/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspDmLib.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-arm/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
}

do_install_append_puma6 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspDmLib.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-arm/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${bindir}/CcspPandMSsp \
    ${prefix}/ccsp/pam/CcspPandMSsp \
    ${prefix}/ccsp/pam/CcspDmLib.cfg \
    ${prefix}/ccsp/pam/CcspPam.cfg \
    ${prefix}/ccsp/pam/COSAXcalibur.XML \
    ${prefix}/ccsp/pam/TR181-USGv2.XML  \
    ${libdir}/libtr181.so* \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/pam/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
