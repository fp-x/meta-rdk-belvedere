SUMMARY = "CCSP CcspCMAgent component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCMAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library hal"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspCMAgent.git;protocol=git;branch=daisy;rev=daisy \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ??= "ccsp-common-library"

export INCLUDES = " -I${STAGING_DIR_HOST}/usr/include/dbus-1.0 \
 -I${STAGING_DIR_HOST}/usr/lib/dbus-1.0/include \
 -I${STAGING_DIR_HOST}/usr/include/ccsp \
"

export LDFLAGS = " -L${STAGING_DIR_HOST}/usr/lib \
 -ldbus-1 \
"

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/cm
    install -m 777 ${D}/usr/bin/CcspCMAgentSsp -t ${D}/usr/ccsp/cm
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/CcspCMDM_pc.cfg ${D}/usr/ccsp/cm/CcspCMDM.cfg
    install -m 644 ${WORKDIR}/git/config/CcspCM_pc.cfg ${D}/usr/ccsp/cm/CcspCM.cfg
    install -m 644 ${WORKDIR}/git/config/TR181-CM_pc.XML ${D}/usr/ccsp/cm/TR181-CM.XML
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/CcspCMDM_arm.cfg ${D}/usr/ccsp/cm/CcspCMDM.cfg
    install -m 644 ${WORKDIR}/git/config/CcspCM_arm.cfg ${D}/usr/ccsp/cm/CcspCM.cfg
    install -m 644 ${WORKDIR}/git/config/TR181-CM_arm.XML ${D}/usr/ccsp/cm/TR181-CM.XML
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/CcspCMDM_arm.cfg ${D}/usr/ccsp/cm/CcspCMDM.cfg
    install -m 644 ${WORKDIR}/git/config/CcspCM_arm.cfg ${D}/usr/ccsp/cm/CcspCM.cfg
    install -m 644 ${WORKDIR}/git/config/TR181-CM_arm.XML ${D}/usr/ccsp/cm/TR181-CM.XML
}

FILES_${PN} = " \
    /usr/ccsp/cm/CcspCMAgentSsp \
    /usr/ccsp/cm/CcspCMDM.cfg \
    /usr/ccsp/cm/CcspCM.cfg \
    /usr/ccsp/cm/TR181-CM.XML \
"
