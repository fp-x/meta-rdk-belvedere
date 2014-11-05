SUMMARY = "CCSP PsmSsp component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspPsm"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
git://github.com/ccsp-yocto/CcspPsm.git;protocol=git;branch=daisy;rev=daisy \
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

do_configure_append_qemux86 () {
    install -m 644 ${WORKDIR}/git/source-pc/ssp_HAL_apis.c ${WORKDIR}/git/source/Ssp/psm_hal_apis.c
}

do_configure_append_qemuarm () {
    install -m 644 ${WORKDIR}/git/source-arm/psm_hal_apis.c -t ${WORKDIR}/git/source/Ssp
}

do_configure_append_raspberrypi () {
    install -m 644 ${WORKDIR}/git/source-arm/psm_hal_apis.c -t ${WORKDIR}/git/source/Ssp
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/config
    install -m 777 ${D}/usr/bin/PsmSsp -t ${D}/usr/ccsp
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/bbhm_def_cfg_pc.xml ${D}/usr/ccsp/config/bbhm_def_cfg.xml
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/bbhm_def_cfg_arm.xml ${D}/usr/ccsp/config/bbhm_def_cfg.xml
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/bbhm_def_cfg_arm.xml ${D}/usr/ccsp/config/bbhm_def_cfg.xml
}

FILES_${PN} = " \
    /usr/ccsp/PsmSsp \
    /usr/ccsp/config/bbhm_def_cfg.xml \
"

