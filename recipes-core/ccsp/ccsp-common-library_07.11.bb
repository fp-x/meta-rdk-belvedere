SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "dbus openssl"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspCommonLibrary.git;protocol=git;branch=daisy;rev=daisy \
file://01-support-newer-dbus-apis.patch \
file://02-support-dbus-ccsp-apis.patch \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ??= "dbus"

export INCLUDES = " -I${STAGING_DIR_HOST}/usr/include/dbus-1.0 \
 -I${STAGING_DIR_HOST}/usr/lib/dbus-1.0/include \
"
export LDFLAGS = " -L${STAGING_DIR_HOST}/usr/lib \
 -ldbus-1 \
"

do_install_append () {
    install -d ${D}/usr/include/ccsp
    install -d ${D}/usr/include/ccsp/linux
    install -m 644 ${WORKDIR}/git/source/debug_api/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/ansc/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/asn.1/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/http/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/slap/components/SlapVarConverter/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/stun/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/tls/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/util_api/web/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/cosa/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/cosa/package/slap/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/cosa/package/system/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/cosa/include/linux/*.h ${D}/usr/include/ccsp/linux
    install -m 644 ${WORKDIR}/git/source/cosa/include/linux/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/ccsp/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/ccsp/custom/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/ccsp/components/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/ccsp/components/common/MessageBusHelper/include/*.h ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/source/ccsp/components/common/PoamIrepFolder/*.h ${D}/usr/include/ccsp

    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -d ${D}/usr/ccsp/cm
    install -d ${D}/usr/ccsp/mta
    install -d ${D}/usr/ccsp/pam
    install -d ${D}/usr/ccsp/tr069pa
    install -m 777 ${WORKDIR}/git/scripts/cosa_stop.sh -t ${D}/usr/ccsp
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 777 ${WORKDIR}/git/scripts/cli_start_pc.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${WORKDIR}/git/scripts/cosa_start_pc.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${WORKDIR}/git/config/basic_pc.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

do_install_append_atom () {
    # Config files and scripts
    install -m 777 ${WORKDIR}/git/scripts/cli_start_atom.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${WORKDIR}/git/scripts/cosa_start_atom.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${WORKDIR}/git/config/basic_atom.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_atom.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_atom.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_atom.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_atom.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 777 ${WORKDIR}/git/scripts/cli_start_arm.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${WORKDIR}/git/scripts/cosa_start_arm.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${WORKDIR}/git/config/basic_arm.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 777 ${WORKDIR}/git/scripts/cli_start_arm.sh ${D}/usr/ccsp/cli_start.sh 
    install -m 777 ${WORKDIR}/git/scripts/cosa_start_arm.sh ${D}/usr/ccsp/cosa_start.sh 
    install -m 644 ${WORKDIR}/git/config/basic_arm.conf ${D}/usr/ccsp/basic.conf 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/cm/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/mta/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/pam/ccsp_msg.cfg 
    install -m 644 ${WORKDIR}/git/config/ccsp_msg_arm.cfg ${D}/usr/ccsp/tr069pa/ccsp_msg.cfg 
}

FILES_${PN} += " \
    /usr/ccsp/basic.conf \
    /usr/ccsp/cli_start.sh \
    /usr/ccsp/cosa_start.sh \
    /usr/ccsp/cosa_stop.sh \
    /usr/ccsp/cm/ccsp_msg.cfg \
    /usr/ccsp/mta/ccsp_msg.cfg \
    /usr/ccsp/pam/ccsp_msg.cfg \
    /usr/ccsp/tr069pa/ccsp_msg.cfg \
"

