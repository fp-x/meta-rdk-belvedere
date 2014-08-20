SUMMARY = "A console-only image to test the CCSP yocto build"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

include core-image-minimal.bb
#include rpi-basic-image.bb

RDEPENDS_${PN} += " \
	CcspCommonLibrary \
	hal \
	CcspCMAgent \
	CcspCr \
	CcspLMLite \
	CcspMisc \
	CcspMtaAgent \
	CcspPandM \
	CcspPsm \
	CcspSnmpPa \
	CcspTr069Pa \
	CcspWifiAgent \
	RebootManager \
	TestAndDiagnostic \
	"

IMAGE_INSTALL += " \
	CcspCommonLibrary \
	hal \
	CcspCMAgent \
	CcspCr \
	CcspLMLite \
	CcspMisc \
	CcspMtaAgent \
	CcspPandM \
	CcspPsm \
	CcspSnmpPa \
	CcspTr069Pa \
	CcspWifiAgent \
	RebootManager \
	TestAndDiagnostic \
	"

export IMAGE_BASENAME = "ccsp-test-image"

