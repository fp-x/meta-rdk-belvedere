SUMMARY = "A console-only image to test the CCSP yocto build"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

IMAGE_FEATURES += "package-management"

IMAGE_ROOTFS_SIZE = "8192"

#Base this image on core-image-minimal
include recipes-core/images/core-image-minimal.bb

#require ${HOME}/poky/meta/recipes-core/images/core-image-minimal.bb
#require ${HOME}/poky/meta-raspberrypi/recipes-core/images/rpi-basic-image.bb

IMAGE_INSTALL_append += " \
    packagegroup-rdkb-ccsp \
	"

IMAGE_INSTALL_append_qemux86 += " \
	"

IMAGE_INSTALL_append_qemuarm += " \
	"

IMAGE_INSTALL_append_raspberrypi += " \
    kernel-modules \
	"

export IMAGE_BASENAME = "ccsp-test-image"

