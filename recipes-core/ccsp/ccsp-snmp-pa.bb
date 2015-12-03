SUMMARY = "CCSP CcspSnmpPa component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspSnmpPa"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library net-snmp openssl"

require ccsp_common.inc

SRC_URI = "${RDKB_CCSP_ROOT_GIT}/CcspSnmpPa${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=CcspSnmpPa"

SRCREV_CcspSnmpPa = "${AUTOREV}"
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
    install -d ${D}/usr/ccsp/snmp
    install -m 644 ${S}/config/snmpd.conf -t ${D}/usr/ccsp/snmp
    install -m 777 ${S}/scripts/run_snmpd.sh -t ${D}/usr/ccsp/snmp
    install -m 777 ${S}/scripts/run_subagent.sh -t ${D}/usr/ccsp/snmp
    install -m 644 ${S}/Mib2DmMapping/Ccsp*.xml -t ${D}/usr/ccsp/snmp
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/snmp/snmpd.conf \
    ${prefix}/ccsp/snmp/run_snmpd.sh \
    ${prefix}/ccsp/snmp/run_subagent.sh \
    ${prefix}/ccsp/snmp/Ccsp*.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/snmp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
