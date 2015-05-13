SUMMARY = "portable Universal Plug and Play SDK"
HOMEPAGE = "http://pupnp.sourceforge.net/"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d41d8cd98f00b204e9800998ecf8427e"

SRC_URI = "http://sourceforge.net/projects/pupnp/files/pupnp/libUPnP%20${PV}/${BPN}-${PV}.tar.bz2 \
    "

SRC_URI[md5sum] = "8918dcf7428cd119d0c8275765ff2833"
SRC_URI[sha256sum] = "58d7cabec2b21c80e28a4e5090bba94a849a8f02450e26c1b985318a36b0bbb3"

PACKAGECONFIG ??= ""

inherit autotools pkgconfig


