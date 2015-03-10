SUMMARY = "CCSP CcspCrSsp component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCr"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
    git://github.com/ccsp-yocto/CcspCr.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
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

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -m 777 ${D}/usr/bin/CcspCrSsp -t ${D}/usr/ccsp
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/cr-deviceprofile_pc.xml ${D}/usr/ccsp/cr-deviceprofile.xml
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/cr-deviceprofile_arm.xml ${D}/usr/ccsp/cr-deviceprofile.xml
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/cr-deviceprofile_arm.xml ${D}/usr/ccsp/cr-deviceprofile.xml
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${prefix}/ccsp/CcspCrSsp \
    ${prefix}/ccsp/cr-deviceprofile.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
