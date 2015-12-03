SUMMARY = "CCSP Utopia"
HOMEPAGE = "http://github.com/belvedere-yocto/Utopia"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"


DEPENDS = "ccsp-common-library virtual/ccsp-hal zlib dbus"
require recipes-core/ccsp/ccsp_common.inc
SRC_URI = "${RDKB_CCSP_ROOT_GIT}/Utopia${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH};name=Utopia"

SRCREV_Utopia = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS += " \
    -I${STAGING_INCDIR}/ccsp \
    "

do_install_arm_files () {
    # Config files and scripts
    install -d ${D}${sysconfdir}/utopia/service.d
    install -d ${D}${sysconfdir}/utopia/registration.d
    install -d ${D}${sysconfdir}/utopia/post.d
    install -d ${D}${sbindir}/
    install -d ${D}${sysconfdir}/IGD
    install -d ${D}${sysconfdir}/utopia/service.d/service_bridge
    install -d ${D}${sysconfdir}/utopia/service.d/service_ddns
    install -d ${D}${sysconfdir}/utopia/service.d/service_dhcp_server
    install -d ${D}${sysconfdir}/utopia/service.d/service_lan
    install -d ${D}${sysconfdir}/utopia/service.d/service_multinet
    install -d ${D}${sysconfdir}/utopia/service.d/service_syslog
    install -d ${D}${sysconfdir}/utopia/service.d/service_wan
    install -m 755 ${WORKDIR}/git/source/scripts/init/system/utopia_init.sh ${D}${sysconfdir}/utopia/
    install -m 644 ${WORKDIR}/git/source/scripts/init/defaults/system_defaults_arm ${D}${sysconfdir}/utopia/system_defaults
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/*.sh ${D}${sysconfdir}/utopia/service.d/
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_bridge/*.sh ${D}${sysconfdir}/utopia/service.d/service_bridge
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_ddns/*.sh ${D}${sysconfdir}/utopia/service.d/service_ddns
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_dhcp_server/* ${D}${sysconfdir}/utopia/service.d/service_dhcp_server
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_lan/*.sh ${D}${sysconfdir}/utopia/service.d/service_lan
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_multinet/*.sh ${D}${sysconfdir}/utopia/service.d/service_multinet
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_syslog/*.sh ${D}${sysconfdir}/utopia/service.d/service_syslog
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_wan/*.sh ${D}${sysconfdir}/utopia/service.d/service_wan
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_firewall/firewall_log_handle.sh ${D}${sysconfdir}/utopia/service.d/
    install -m 644 ${WORKDIR}/git/source/igd/src/inc/*.xml ${D}${sysconfdir}/IGD
    install -m 644 ${WORKDIR}/git/source/scripts/init/syslog_conf/syslog.conf_default ${D}${sysconfdir}/
    install -m 755 ${WORKDIR}/git/source/scripts/init/syslog_conf/log_start.sh ${D}${sbindir}/
    install -m 755 ${WORKDIR}/git/source/scripts/init/syslog_conf/log_handle.sh ${D}${sbindir}/
    install -m 755 ${WORKDIR}/git/source/scripts/init/syslog_conf/syslog_conf_tool.sh ${D}${sbindir}/
    install -m 644 ${WORKDIR}/git/source/scripts/init/service.d/event_flags ${D}${sysconfdir}/utopia/service.d/
    install -m 644 ${WORKDIR}/git/source/scripts/init/service.d/rt_tables ${D}${sysconfdir}/utopia/service.d/rt_tables
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_cosa_arm.sh ${D}${sysconfdir}/utopia/service.d/service_cosa.sh
    install -m 755 ${WORKDIR}/git/source/scripts/init/service.d/service_dhcpv6_client_arm.sh ${D}${sysconfdir}/utopia/service.d/service_ipv6_client.sh

    # Creating symbolic links to install files in specific directory as in legacy builds
    ln -sf /usr/bin/10_firewall ${D}${sysconfdir}/utopia/post.d/10_firewall
    ln -sf /usr/bin/10_mcastproxy ${D}${sysconfdir}/utopia/post.d/10_mcastproxy
    ln -sf /usr/bin/10_mldproxy ${D}${sysconfdir}/utopia/post.d/10_mldproxy
    ln -sf /usr/bin/15_igd ${D}${sysconfdir}/utopia/post.d/15_igd
    ln -sf /usr/bin/15_misc ${D}${sysconfdir}/utopia/post.d/15_misc
    ln -sf /usr/bin/02_bridge ${D}${sysconfdir}/utopia/registration.d/02_bridge
    ln -sf /usr/bin/02_forwarding ${D}${sysconfdir}/utopia/registration.d/02_forwarding
    ln -sf /usr/bin/02_ipv4 ${D}${sysconfdir}/utopia/registration.d/02_ipv4
    ln -sf /usr/bin/02_lanHandler ${D}${sysconfdir}/utopia/registration.d/02_lanHandler
    ln -sf /usr/bin/02_multinet ${D}${sysconfdir}/utopia/registration.d/02_multinet
    ln -sf /usr/bin/02_wan ${D}${sysconfdir}/utopia/registration.d/02_wan
    ln -sf /usr/bin/15_ccsphs ${D}${sysconfdir}/utopia/registration.d/15_ccsphs
    ln -sf /usr/bin/15_ddnsclient ${D}${sysconfdir}/utopia/registration.d/15_ddnsclient
    ln -sf /usr/bin/15_dhcp_server ${D}${sysconfdir}/utopia/registration.d/15_dhcp_server
    ln -sf /usr/bin/15_hotspot ${D}${sysconfdir}/utopia/registration.d/15_hotspot
    ln -sf /usr/bin/15_ssh_server ${D}${sysconfdir}/utopia/registration.d/15_ssh_server
    ln -sf /usr/bin/15_wecb ${D}${sysconfdir}/utopia/registration.d/15_wecb
    ln -sf /usr/bin/20_routing ${D}${sysconfdir}/utopia/registration.d/20_routing
    ln -sf /usr/bin/25_crond ${D}${sysconfdir}/utopia/registration.d/25_crond
    ln -sf /usr/bin/26_potd ${D}${sysconfdir}/utopia/registration.d/26_potd
    ln -sf /usr/bin/33_cosa ${D}${sysconfdir}/utopia/registration.d/33_cosa
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_create
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_destroy
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_format
    ln -sf /usr/bin/syscfg ${D}${bindir}/syscfg_check
}

do_install_append () {
    install -d ${D}${bindir}/env
#    install -D -m 0644 ${S}/source/include/autoconf.h ${D}${includedir}/utctx/autoconf.h
    install -D -m 0644 ${S}/source/ulog/ulog.h ${D}${includedir}/ulog/ulog.h
    install -D -m 0644 ${S}/source/utapi/lib/utapi.h ${D}${includedir}/utapi/utapi.h
    install -D -m 0644 ${S}/source/utapi/lib/utapi_wlan.h ${D}${includedir}/utapi/utapi_wlan.h
    install -D -m 0644 ${S}/source/utapi/lib/utapi_util.h ${D}${includedir}/utapi/utapi_util.h
    install -D -m 0644 ${S}/source/utctx/lib/utctx.h ${D}${includedir}/utctx/utctx.h
    install -D -m 0644 ${S}/source/utctx/lib/utctx_api.h ${D}${includedir}/utctx/utctx_api.h
    install -D -m 0644 ${S}/source/utctx/lib/utctx_rwlock.h ${D}${includedir}/utctx/utctx_rwlock.h
    install -D -m 0644 ${S}/source/syscfg/lib/syscfg.h ${D}${includedir}/syscfg/syscfg.h
    install -D -m 0644 ${S}/source/sysevent/lib/sysevent.h ${D}${includedir}/sysevent/sysevent.h
    install -D -m 0644 ${S}/source/sysevent/lib/libsysevent_internal.h ${D}${includedir}/sysevent/libsysevent_internal.h
    install -D -m 0644 ${S}/source/utapi/lib/utapi_tr_dhcp.h ${D}${includedir}/utapi/utapi_tr_dhcp.h
    install -m 0644 ${S}/source/utapi/lib/*.h ${D}${includedir}/utapi/
}

do_install_append_qemuarm () {
    do_install_arm_files
}

do_install_append_raspberrypi () {
    do_install_arm_files
}

do_install_append_armeb () {
    do_install_arm_files
}

do_install_append_puma6 () {
    do_install_arm_files
}

FILES_${PN} += "${sysconfdir}/utopia/"
FILES_${PN} += "${sysconfdir}/IGD/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/"
FILES_${PN} += "${sysconfdir}/utopia/registrartion.d/"
FILES_${PN} += "${sysconfdir}/utopia/post.d/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_bridge/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_ddns/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_dhcp_server/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_lan/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_multinet/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_syslog/"
FILES_${PN} += "${sysconfdir}/utopia/service.d/service_wan/"

FILES_${PN}-dbg += " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
