SUMMARY = "A console-only image for the RDK-B yocto build"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

IMAGE_FEATURES += " package-management"

IMAGE_ROOTFS_SIZE = "8192"

#Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL_append += " \
    packagegroup-rdkb-ccsp \
    python-core \
    python-modules \
    perl \
    connman \
    wireless-tools \
    wpa-supplicant \
	"

IMAGE_INSTALL_append_qemux86 += " \
	"

IMAGE_INSTALL_append_qemuarm += " \
	"

IMAGE_INSTALL_append_raspberrypi += " \
    kernel-modules \
	"

export IMAGE_BASENAME = "rdkb"
