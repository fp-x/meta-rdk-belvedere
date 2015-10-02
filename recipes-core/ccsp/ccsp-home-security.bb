SUMMARY = "CCSP Home Security."
HOMEPAGE = "http://github.com/belvedere-yocto/CcspHomeSecurity"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d41d8cd98f00b204e9800998ecf8427e"

DEPENDS = "libxml2 ccsp-common-library utopia curl"

SRC_URI = "\
    git://github.com/belvedere-yocto/CcspHomeSecurity.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

CFLAGS_append = " \
    -I=${includedir}/dbus-1.0 \
    -I=${libdir}/dbus-1.0/include \
    -I=${includedir}/ccsp \
    -I=${includedir}/libxml2 \
    "

CFLAGS_append_qemux86 += -D_COSA_SIM_

do_install_append () {
    # Config files and scripts
    install -d ${D}/usr/ccsp
    install -m 777 ${D}/usr/bin/CcspHomeSecurity -t ${D}/usr/ccsp
}

PACKAGES += "${PN}-ccsp"

FILES_${PN} = " \
    ${bindir}/CcspHomeSecurity \
    ${prefix}/ccsp/CcspHomeSecurity \
"

FILES_${PN}-dbg = " \
    ${prefix}/ccsp/.debug \
    ${prefix}/src/debug \
    ${bindir}/.debug \
    ${libdir}/.debug \
"

