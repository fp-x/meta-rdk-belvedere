SUMMARY = "HAL for RDK CCSP components"
HOMEPAGE = "http://github.com/belvedere-yocto/hal"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

SRC_URI = "\
    git://github.com/belvedere-yocto/hal.git;protocol=git;branch=${CCSP_GIT_BRANCH} \
    "

SRCREV = "${AUTOREV}"
PV = "${RDK_RELEASE}+git${SRCPV}"

S = "${WORKDIR}/git"
CFLAGS_prepend = "-I${S}/include"

inherit autotools

do_install_append () {
    install -d ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/include/*.h ${D}/usr/include/ccsp
}

