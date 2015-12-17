SUMMARY = "CCSP MTA Agent"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspMtaAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library virtual/ccsp-hal"

require ccsp_common.inc

SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspMtaAgent${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspMtaAgent"

SRCREV_CcspMtaAgent = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    "

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/mta
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-pc/CcspMtaAgent.xml ${D}/usr/ccsp/mta/CcspMtaAgent.xml 
    install -m 644 ${WORKDIR}/git/config-pc/CcspMta.cfg ${D}/usr/ccsp/mta/CcspMta.cfg 
    install -m 644 ${WORKDIR}/git/config-pc/CcspMtaLib.cfg ${D}/usr/ccsp/mta/CcspMtaLib.cfg 
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaAgent.xml ${D}/usr/ccsp/mta/CcspMtaAgent.xml 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMta.cfg ${D}/usr/ccsp/mta/CcspMta.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaLib.cfg ${D}/usr/ccsp/mta/CcspMtaLib.cfg 
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaAgent.xml ${D}/usr/ccsp/mta/CcspMtaAgent.xml 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMta.cfg ${D}/usr/ccsp/mta/CcspMta.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaLib.cfg ${D}/usr/ccsp/mta/CcspMtaLib.cfg 
}

do_install_append_armeb () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaAgent.xml ${D}/usr/ccsp/mta/CcspMtaAgent.xml 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMta.cfg ${D}/usr/ccsp/mta/CcspMta.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaLib.cfg ${D}/usr/ccsp/mta/CcspMtaLib.cfg 
}

do_install_append_puma6 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaAgent.xml ${D}/usr/ccsp/mta/CcspMtaAgent.xml 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMta.cfg ${D}/usr/ccsp/mta/CcspMta.cfg 
    install -m 644 ${WORKDIR}/git/config-arm/CcspMtaLib.cfg ${D}/usr/ccsp/mta/CcspMtaLib.cfg 
}

do_install_append_mips () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config-mips/CcspMtaAgent.xml ${D}/usr/ccsp/mta/CcspMtaAgent.xml 
    install -m 644 ${WORKDIR}/git/config-mips/CcspMta.cfg ${D}/usr/ccsp/mta/CcspMta.cfg 
    install -m 644 ${WORKDIR}/git/config-mips/CcspMtaLib.cfg ${D}/usr/ccsp/mta/CcspMtaLib.cfg 
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${prefix}/ccsp/mta/CcspMtaAgent.xml \
    ${prefix}/ccsp/mta/CcspMta.cfg \
    ${prefix}/ccsp/mta/CcspMtaLib.cfg \
    /usr/bin/ \
    /usr/lib/ \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/mta/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
