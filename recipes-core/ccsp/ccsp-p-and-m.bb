SUMMARY = "CCSP PandMSsp component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspPandM"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library utopia"

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
    "

CFLAGS_append_qemux86 += "-D_COSA_SIM_"

LDFLAGS_append = " \
    -ldbus-1 \
    "

do_install_pc_sources () {
    echo "=================== running do_install_pc_sources..."
    install -d ${WORKDIR}/git/source/TR-181/board_include
    install -d ${WORKDIR}/git/source/TR-181/board_sbapi
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_include/dml_tr181_custom_cfg.h -t ${WORKDIR}/git/source/TR-181/board_include/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_bridging_apis.c
    -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_bridging_apis_ext.h -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_deviceinfo_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_dhcpv4_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_dhcpv6_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_dns_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_drg_common.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_ethernet_apis.c
    -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_ethernet_apis_ext.h -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_firewall_apis.c
    -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_gatewayinfo_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_hosts_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_interfacestack_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_ip_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_ipv6rd_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_moca_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_nat_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_neighdisc_apis.c
    -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_ppp_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_ra_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_routing_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_time_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_upnp_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_userinterface_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_users_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_cisco_com_ddns_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_cisco_com_devicecontrol_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_cisco_com_diagnostics_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_cisco_com_mld_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_cisco_com_rlog_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_cisco_com_security_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/cosa_x_comcast_com_parentalcontrol_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-pc/TR-181/board_sbapi/Makefile.am -t ${WORKDIR}/git/source/TR-181/board_sbapi/
}

do_install_arm_sources () {
    echo "=================== running do_install_arm_sources..."
    install -d ${WORKDIR}/git/source/TR-181/board_include
    install -d ${WORKDIR}/git/source/TR-181/board_ml
    install -d ${WORKDIR}/git/source/TR-181/board_sbapi
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_include/dml_tr181_custom_cfg.h -t ${WORKDIR}/git/source/TR-181/board_include/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_ml/cosa_x_cisco_com_filetransfer_dml.c -t ${WORKDIR}/git/source/TR-181/board_ml/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_ml/cosa_x_cisco_com_filetransfer_dml.h -t ${WORKDIR}/git/source/TR-181/board_ml/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_ml/cosa_x_cisco_com_filetransfer_internal.c -t ${WORKDIR}/git/source/TR-181/board_ml/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_ml/cosa_x_cisco_com_filetransfer_internal.h -t ${WORKDIR}/git/source/TR-181/board_ml/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_bridging_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_bridging_apis_ext.h -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_common_util.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_common_util.h -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_deviceinfo_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_dhcpv4_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_dhcpv6_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_dns_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_drg_common.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_ethernet_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_ethernet_apis_ext.h -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_firewall_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_gatewayinfo_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_hosts_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_interfacestack_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_ip_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_ipv6rd_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_moca_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_nat_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_neighdisc_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_ppp_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_ra_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_routing_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_time_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_upnp_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_userinterface_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_users_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_ddns_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_devicecontrol_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_diagnostics_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_filetransfer_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_mld_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_rlog_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_cisco_com_security_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
    install -m 644 ${WORKDIR}/git/source-arm/TR-181/board_sbapi/cosa_x_comcast_com_parentalcontrol_apis.c -t ${WORKDIR}/git/source/TR-181/board_sbapi/
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
