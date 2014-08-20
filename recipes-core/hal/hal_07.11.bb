SUMMARY = "HAL for RDK CCSP components"
HOMEPAGE = "http://github.com/ccsp-yocto/hal"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

SRC_URI = "\
git://github.com/ccsp-yocto/hal.git;protocol=git;branch=master \
    "
SRCREV="ebb202630169bd35d72a3c6659d057b7dfec218b"

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"
CFLAGS_prepend = "-I${S}/include"

inherit autotools

do_install_append () {
    install -d ${D}/usr/include/ccsp
    install -m 644 ${WORKDIR}/git/include/*.h ${D}/usr/include/ccsp
}
