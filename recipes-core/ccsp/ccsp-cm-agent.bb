SUMMARY = "CCSP CcspCMAgent component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspCMAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library virtual/ccsp-hal"

require ccsp_common.inc

SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspCMAgent${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspCMAgent"

SRCREV_CcspCMAgent = "${AUTOREV}"
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
    install -d ${D}/usr/ccsp/cm
    install -m 777 ${D}/usr/bin/CcspCMAgentSsp -t ${D}/usr/ccsp/cm
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${S}/config-pc/CcspCMDM.cfg ${D}/usr/ccsp/cm/CcspCMDM.cfg
    install -m 644 ${S}/config-pc/CcspCM.cfg ${D}/usr/ccsp/cm/CcspCM.cfg
    install -m 644 ${S}/config-pc/TR181-CM.XML ${D}/usr/ccsp/cm/TR181-CM.XML
}

do_install_for_arm() {
    # Config files and scripts
    install -m 644 ${S}/config-arm/CcspCMDM.cfg ${D}/usr/ccsp/cm/CcspCMDM.cfg
    install -m 644 ${S}/config-arm/CcspCM.cfg ${D}/usr/ccsp/cm/CcspCM.cfg
    install -m 644 ${S}/config-arm/TR181-CM.XML ${D}/usr/ccsp/cm/TR181-CM.XML
}

do_install_append_qemuarm () {
    do_install_for_arm
}

do_install_append_raspberrypi () {
    do_install_for_arm
}

do_install_append_armeb() {
    do_install_for_arm
}

do_install_append_puma6() {
    do_install_for_arm
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    /usr/ccsp/cm/CcspCMAgentSsp \
    /usr/ccsp/cm/CcspCMDM.cfg \
    /usr/ccsp/cm/CcspCM.cfg \
    /usr/ccsp/cm/TR181-CM.XML \
    /usr/lib/ \
    /usr/bin/ \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/cm/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
