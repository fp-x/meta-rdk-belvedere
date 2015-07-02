SUMMARY = "CCSP Reboot Manager utility."
HOMEPAGE = "http://github.com/belvedere-yocto/RebootManager"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
    git://github.com/belvedere-yocto/RebootManager.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
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

LDFLAGS_append = " \
    -ldbus-1 \
    "

do_configure_append_qemux86 () {
    install -m 644 ${WORKDIR}/git/source-pc/CcspRmHal.c -t ${WORKDIR}/git/source/RmSsp
}

do_configure_append_qemuarm () {
    install -m 644 ${WORKDIR}/git/source-arm/CcspRmHal.c -t ${WORKDIR}/git/source/RmSsp
}

do_configure_append_raspberrypi () {
    install -m 644 ${WORKDIR}/git/source-arm/CcspRmHal.c -t ${WORKDIR}/git/source/RmSsp
}

do_configure_append_puma6 () {
    install -m 644 ${WORKDIR}/git/source-arm/CcspRmHal.c -t ${WORKDIR}/git/source/RmSsp
}

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp/rm
    install -m 777 ${D}/usr/bin/CcspRmSsp -t ${D}/usr/ccsp/rm
}

do_install_append_qemux86 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/RebootManager_pc.xml ${D}/usr/ccsp/rm/RebootManager.xml 
}

do_install_append_qemuarm () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/RebootManager_arm.xml ${D}/usr/ccsp/rm/RebootManager.xml 
}

do_install_append_raspberrypi () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/RebootManager_arm.xml ${D}/usr/ccsp/rm/RebootManager.xml 
}

do_install_append_puma6 () {
    # Config files and scripts
    install -m 644 ${WORKDIR}/git/config/RebootManager_arm.xml ${D}/usr/ccsp/rm/RebootManager.xml 
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${bindir}/CcspRmSsp \
    ${prefix}/ccsp/rm/CcspRmSsp \
    ${prefix}/ccsp/rm/RebootManager.xml \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/rm/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
