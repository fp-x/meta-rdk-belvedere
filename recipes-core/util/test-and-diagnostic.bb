SUMMARY = "CCSP test and diagnostice utilities."
HOMEPAGE = "http://github.com/belvedere-yocto/TestAndDiagnostic"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "ccsp-common-library"

SRC_URI = "\
    git://github.com/belvedere-yocto/TestAndDiagnostic.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
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

do_install_append () {
    # Config files and scripts
    install -D -p -m 777 ${D}/usr/bin/CcspTandDSsp ${D}/usr/ccsp/tad/CcspTandDSsp
}

PACKAGES += "${PN}-ccsp"

FILES_${PN}-ccsp = " \
    ${libdir}/libdiagnostic.so.* \
    ${libdir}/libdmltad.so.* \
    ${bindir}/CcspTandDSsp \
    ${prefix}/ccsp/tad/CcspTandDSsp \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/tad/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"
