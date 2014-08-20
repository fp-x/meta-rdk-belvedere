SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCommonLibrary"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "dbus"

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

FILES_${PN} += " \
    ${WORKDIR}/git/source/util_api/ccsp_msg_bus/basic.conf \
    ${WORKDIR}/git/source/util_api/ccsp_msg_bus/ccsp_msg.cfg \
    ${WORKDIR}/git/scripts/cli_start_pc.sh \
    ${WORKDIR}/git/scripts/cosa_start_pc.sh \
    ${WORKDIR}/git/config/ccsp_msg_pc.cfg \
    ${WORKDIR}/git/config/ccsp_msg_pc.cfg \
    ${WORKDIR}/git/config/ccsp_msg_pc.cfg \
    ${WORKDIR}/git/config/ccsp_msg_pc.cfg \
"

do_install_append () {
    mkdir -p ${D}/usr/include/ccsp/linux
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
    #mkdir -p ${D}/usr/ccsp/cm
    #mkdir -p ${D}/usr/ccsp/mta
    #mkdir -p ${D}/usr/ccsp/pam
    #mkdir -p ${D}/usr/ccsp/tr069pa
    #install -m 644 ${WORKDIR}/git/source/util_api/ccsp_msg_bus/basic.conf -t ${D}/usr/ccsp
    #install -m 644 ${WORKDIR}/git/source/util_api/ccsp_msg_bus/ccsp_msg.cfg -t ${D}/usr/ccsp
    #install -m 644 ${WORKDIR}/git/scripts/cli_start_pc.sh -t ${D}/usr/ccsp
    #install -m 644 ${WORKDIR}/git/scripts/cosa_start_pc.sh -t ${D}/usr/ccsp
    #install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg -t ${D}/usr/ccsp/cm
    #install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg -t ${D}/usr/ccsp/mta
    #install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg -t ${D}/usr/ccsp/pam
    #install -m 644 ${WORKDIR}/git/config/ccsp_msg_pc.cfg -t ${D}/usr/ccsp/tr069pa

}
