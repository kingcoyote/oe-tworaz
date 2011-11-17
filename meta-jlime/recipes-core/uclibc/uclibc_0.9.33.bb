SRCREV="eb51b7f1359cf75c792cca6615c9aa1b167ce79f"

require uclibc.inc
PR = "${INC_PR}.1"
PROVIDES += "virtual/${TARGET_PREFIX}libc-for-gcc"

SRC_URI = "git://uclibc.org/uClibc.git;branch=${PV};protocol=git \
	file://uClibc.machine \
	file://uClibc.distro \
	file://uclibc-arm-ftruncate64.patch \
	file://uclibc_enable_log2_test.patch \
	file://ldso_use_arm_dl_linux_resolve_in_thumb_mode.patch \
	file://powerpc_copysignl.patch \
	file://argp-support.patch \
	file://argp-headers.patch \
	file://remove_attribute_optimize_Os.patch \
	file://compile-arm-fork-with-O2.patch \
	file://orign_path.patch \
	file://0001-mips-signalfd.h-SFD_NONBLOCK-for-mips-is-0200-unlike.patch \
	file://uclibc-execvpe.patch \
	file://sync_file_range2.patch \
	file://mount.h-update.patch \
	"

S = "${WORKDIR}/git"
