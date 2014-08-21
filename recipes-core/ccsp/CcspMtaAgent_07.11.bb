SUMMARY = "CCSP MTA Agent"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspMtaAgent"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "CcspCommonLibrary hal"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspMtaAgent.git;protocol=git;branch=daisy;rev=daisy \
    "

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

PACKAGECONFIG ??= "CcspCommonLibrary"

export INCLUDES = " -I${STAGING_DIR_HOST}/usr/include/dbus-1.0 \
 -I${STAGING_DIR_HOST}/usr/lib/dbus-1.0/include \
 -I${STAGING_DIR_HOST}/usr/include/ccsp \
"

export LDFLAGS = " -L${STAGING_DIR_HOST}/usr/lib \
 -ldbus-1 \
"

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/mta
    install -m 777 ${WORKDIR}/build/source/MtaAgentSsp/CcspMtaAgentSsp -t ${D}/usr/ccsp/mta
    install -m 644 ${WORKDIR}/git/config/CcspMtaAgent_pc.xml -t ${D}/usr/ccsp/mta
    install -m 644 ${WORKDIR}/git/config/CcspMta_pc.cfg -t ${D}/usr/ccsp/mta
    install -m 644 ${WORKDIR}/git/config/CcspMtaLib_pc.cfg -t ${D}/usr/ccsp/mta
}

FILES_${PN} = " \
    /usr/ccsp/mta/CcspMtaAgentSsp \
    /usr/ccsp/mta/CcspMtaAgent_pc.xml \
    /usr/ccsp/mta/CcspMta_pc.cfg \
    /usr/ccsp/mta/CcspMtaLib_pc.cfg \
"

