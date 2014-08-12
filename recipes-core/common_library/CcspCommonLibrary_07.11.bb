SUMMARY = "CCSP libccsp_common component"
HOMEPAGE = "http://github.com/ccsp-yocto/CcspCommonLibrary"

LICENSE = "APACHEv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0c56db0143f4f80c369ee3af7425af6e"

S = "${WORKDIR}/${BPN}-${PV}"


SRC_URI = "\
http://github.com/ccsp-yocto/CcspCommonLibrary.git \
    "

SRC_URI[md5sum] = "65b4e0df4934a6cd08c506cabcbe584f"
SRC_URI[sha256sum] = "22c37dc90c871e8e052b2cab0ad219d010fa938608cd66b21c8f3c759046fa36"

inherit autotools

