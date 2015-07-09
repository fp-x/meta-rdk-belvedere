SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/belvedere-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "dbus openssl"

SRC_URI = "\
    git://github.com/belvedere-yocto/CcspCommonLibrary.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    file://01-support-newer-dbus-apis.patch \
    file://02-support-dbus-ccsp-apis.patch \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    "

LDFLAGS_append = " \
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
    install -m 777 ${S}/scripts/cosa_stop.sh -t ${D}/usr/ccsp
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_pc.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${S}/scripts/cosa_start_pc.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${S}/config-pc/basic.conf ${D}/usr/ccsp/basic.conf
    install -m 644 ${S}/config-pc/ccsp_msg.cfg ${D}/usr/ccsp/ccsp_msg.cfg
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
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/ccsp_msg.cfg
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${S}/config-atom/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_arm.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${S}/scripts/cosa_start_arm.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${S}/config-arm/basic.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_arm.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${S}/scripts/cosa_start_arm.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${S}/config-arm/basic.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

do_install_append_puma6 () {
    # Config files and scripts
    install -m 777 ${S}/scripts/cli_start_arm.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${S}/scripts/cosa_start_arm.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${S}/config-arm/basic.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/ccsp_msg.cfg
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${S}/config-arm/ccsp_msg.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${prefix}/ccsp/basic.conf \
    ${prefix}/ccsp/cli_start.sh \
    ${prefix}/ccsp/cosa_start.sh \
    ${prefix}/ccsp/cosa_stop.sh \
    ${prefix}/ccsp/ccsp_msg.cfg \
    ${prefix}/ccsp/cm/ccsp_msg.cfg \
    ${prefix}/ccsp/mta/ccsp_msg.cfg \
    ${prefix}/ccsp/pam/ccsp_msg.cfg \
    ${prefix}/ccsp/tr069pa/ccsp_msg.cfg \
    ${libdir}/libccsp_common.so* \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
