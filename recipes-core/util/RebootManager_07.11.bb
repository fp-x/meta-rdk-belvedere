SUMMARY = "CCSP Reboot Manager utility."
HOMEPAGE = "http://github.com/ccsp-yocto/RebootManager"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1b9c3a810ba2d91cab5522ca08f70b47"

SRC_URI = "\
git://github.com/ccsp-yocto/RebootManager.git;protocol=git;branch=master \
    "
SRCREV="392c9a9b5b3a7ced83d065dafbfdd3d33d268fe8"

SRC_URI[md5sum] = "d338d61e396d5038025339bf5bdb169d"
SRC_URI[sha256sum] = "e6f5a166c0e0f775dc09261f992abb561b781f4a992ef2c0081edcf6b265df24"

S = "${WORKDIR}/git"

inherit autotools

