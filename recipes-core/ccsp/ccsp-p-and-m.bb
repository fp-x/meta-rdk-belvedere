SUMMARY = "CCSP PandMSsp component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspPandM"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library ccsp-lm-lite"
DEPENDS_append_puma6 = " utopia virtual/ccsp-hal curl tr69"

require ccsp_common.inc

SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspPandM${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspPandM"

SRCREV_CcspPandM = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I${STAGING_INCDIR} \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    -I${STAGING_INCDIR}/ccsp \
    -I${STAGING_INCDIR}/utapi \
    -I${STAGING_INCDIR}/utctx \
    -I${STAGING_INCDIR}/ulog \
    -I${STAGING_INCDIR}/syscfg \
    "

CFLAGS_append += "-DCONFIG_VENDOR_CUSTOMER_COMCAST -DCONFIG_INTERNET2.0 -DCONFIG_CISCO_HOTSPOT"
 
LDFLAGS_append = " \
    -ldbus-1 \
    -lutctx \
    -lutapi \
    "

do_install_pc_sources () {
    echo "=================== running do_install_pc_sources..."
    install -m 644 ${WORKDIR}/git/Makefile.am.pc ${WORKDIR}/git/Makefile.am
}

do_install_pc_config () {
    echo "=================== running do_install_pc_config..."
    install -m 644 ${WORKDIR}/git/config-pc/CcspDmLib.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg
    install -m 644 ${WORKDIR}/git/config-pc/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-pc/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
}

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -m 644 ${WORKDIR}/git/Makefile.am.arm ${WORKDIR}/git/Makefile.am
}

do_install_arm_config () {
    echo "=================== running do_install_arm_config..."
    install -m 644 ${WORKDIR}/git/config-arm/CcspDmLib.cfg ${D}/usr/ccsp/pam/CcspDmLib.cfg
    install -m 644 ${WORKDIR}/git/config-arm/CcspPam.cfg -t ${D}/usr/ccsp/pam
    install -m 644 ${WORKDIR}/git/config-arm/TR181-USGv2.XML -t ${D}/usr/ccsp/pam
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

do_install_append_armeb () {
    do_install_arm_sources
}

do_configure_prepend_puma6 () {
    do_install_arm_sources
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/pam
    install -m 755 ${S}/scripts/email_notification_monitor.sh ${D}/usr/ccsp/pam/email_notification_monitor.sh
    install -m 644 ${WORKDIR}/git/config-pc/COSAXcalibur.XML -t ${D}/usr/ccsp/pam
}

do_install_append_qemux86 () {
    do_install_pc_config
}

do_install_append_qemuarm () {
    do_install_arm_config
}

do_install_append_raspberrypi () {
    do_install_arm_config
}

do_install_append_armeb () {
    do_install_arm_config
}

do_install_append_puma6 () {
    do_install_arm_config
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${prefix}/ccsp/pam/CcspDmLib.cfg \
    ${prefix}/ccsp/pam/CcspPam.cfg \
    ${prefix}/ccsp/pam/COSAXcalibur.XML \
    ${prefix}/ccsp/pam/TR181-USGv2.XML  \
    ${prefix}/ccsp/pam/email_notification_monitor.sh  \
    /usr/bin/ \
    /usr/lib/ \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/pam/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
