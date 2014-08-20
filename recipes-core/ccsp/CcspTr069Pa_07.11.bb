SUMMARY = "CCSP Tr069Pa component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspPsm"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "CcspCommonLibrary"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspTr069Pa.git;protocol=git;branch=daisy;rev=daisy \
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

FILES_${PN} = " \
    ${WORKDIR}/config/ccsp_tr069_pa_certificate_cfg_pc.xml \
    ${WORKDIR}/config/ccsp_tr069_pa_cfg_pc.xml \
    ${WORKDIR}/config/ccsp_tr069_pa_mapper_pc.xml \
    ${WORKDIR}/config/sdm.xml \
"

do_install_append () {
    # Config files and scripts
    #mkdir -p ${D}/usr/ccsp/tr069pa
    #install -m 644 ${WORKDIR}/config/ccsp_tr069_pa_certificate_cfg_pc.xml -t ${D}/usr/ccsp/tr069pa
    #install -m 644 ${WORKDIR}/config/ccsp_tr069_pa_cfg_pc.xml -t ${D}/usr/ccsp/tr069pa
    #install -m 644 ${WORKDIR}/config/ccsp_tr069_pa_mapper_pc.xml -t ${D}/usr/ccsp/tr069pa
    #install -m 644 ${WORKDIR}/config/sdm.xml -t ${D}/usr/ccsp/tr069pa
}

