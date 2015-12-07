SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "dbus openssl"

require ccsp_common.inc

SRC_URI = " \
    ${RDKB_CCSP_ROOT_GIT}/CcspCommonLibrary${CCSP_EXT};protocol=${RDK_GIT_PROTOCOL};branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools systemd

CFLAGS += " \
    -I${STAGING_INCDIR}/dbus-1.0 \
    -I${STAGING_LIBDIR}/dbus-1.0/include \
    "

LDFLAGS += " \
    -ldbus-1 \
    "

do_install_append () {
    install -d ${D}/usr/include/ccsp
    install -d ${D}/usr/include/ccsp/linux
    install -m 644 ${S}/source/debug_api/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/ansc/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/asn.1/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/http/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/slap/components/SlapVarConverter/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/stun/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/tls/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/util_api/web/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/package/slap/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/package/system/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/cosa/include/linux/*.h ${D}/usr/include/ccsp/linux
    install -m 644 ${S}/source/cosa/include/linux/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/custom/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/components/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/components/common/MessageBusHelper/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${S}/source/ccsp/components/common/PoamIrepFolder/*.h ${D}/usr/include/ccsp

    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -d ${D}/usr/ccsp/cm
    install -d ${D}/usr/ccsp/mta
    install -d ${D}/usr/ccsp/pam
    install -d ${D}/usr/ccsp/tr069pa
    install -d ${D}/usr/ccsp/wecb
    install -D -m 755 ${S}/scripts/cosa_stop.sh ${D}/usr/ccsp/cosa_stop.sh
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_pc.sh ${D}/usr/ccsp/cli_start.sh
    install -m 777 ${S}/scripts/cosa_start_pc.sh ${D}/usr/ccsp/cosa_start.sh
    install -m 644 ${S}/config-pc/basic.conf ${D}/usr/ccsp/basic.conf
    install -m 644 ${S}/config-pc/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg
    install -m 644 ${S}/config-pc/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg
    install -m 644 ${S}/config-pc/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg
    install -m 644 ${S}/config-pc/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg
}

do_install_append_atom () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_atom.sh ${D}/usr/ccsp/cli_start.sh
    install -m 777 ${S}/scripts/cosa_start_atom.sh ${D}/usr/ccsp/cosa_start.sh
    install -m 644 ${S}/config-atom/basic.conf ${D}/usr/ccsp/basic.conf
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg
}

do_install_for_arm () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_arm.sh ${D}/usr/ccsp/cli_start.sh
    install -m 777 ${S}/scripts/cosa_start_arm.sh ${D}/usr/ccsp/cosa_start.sh
    install -m 644 ${S}/config-arm/basic.conf ${D}/usr/ccsp/basic.conf
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/wecb/ccsp_msg.cfg
}

do_install_append_qemuarm () {
    do_install_for_arm
}

do_install_append_raspberrypi () {
    do_install_for_arm
}

do_install_append_armeb () {
    do_install_for_arm
}

do_install_append_puma6 () {
    do_install_for_arm
    
    # also install for Arris-expected file locations...
    install -d ${IMAGE_ROOTFS}/fss/gw/usr/ccsp
    install -d ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/cm
    install -d ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/mta
    install -d ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/pam
    install -d ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/tr069pa
    install -d ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/wecb
    install -m 777 ${S}/scripts/cosa_stop.sh -t ${IMAGE_ROOTFS}/fss/gw/usr/ccsp

    install -m 777 ${S}/scripts/cli_start_arm.sh ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/cli_start.sh
    install -m 777 ${S}/scripts/cosa_start_arm.sh ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/cosa_start.sh
    install -m 644 ${S}/config-arm/basic.conf ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/basic.conf
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/cm/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/mta/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/pam/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/tr069pa/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${IMAGE_ROOTFS}/fss/gw/usr/ccsp/wecb/ccsp_msg.cfg
}

PACKAGES =+ "ccsp-common-startup"

FILES_ccsp-common-startup = " \
    ${exec_prefix}/ccsp/basic.conf \
    ${exec_prefix}/ccsp/cli_start.sh \
    ${exec_prefix}/ccsp/cosa_*.sh \
    ${exec_prefix}/ccsp/ccsp_msg.cfg \
    ${exec_prefix}/ccsp/cm/ccsp_msg.cfg \
    ${exec_prefix}/ccsp/mta/ccsp_msg.cfg \
    ${exec_prefix}/ccsp/pam/ccsp_msg.cfg \
    ${exec_prefix}/ccsp/tr069pa/ccsp_msg.cfg \
    ${exec_prefix}/ccsp/wecb/ccsp_msg.cfg \
"
FILES_${PN} += "/usr/ccsp/wecb"

FILES_${PN}-dbg = " \
    ${exec_prefix}/ccsp/.debug \
    ${exec_prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
