#!/usr/bin/env -S PYTHONPATH=../../../tools/extract-utils python3
#
# SPDX-FileCopyrightText: 2024 The LineageOS Project
# SPDX-License-Identifier: Apache-2.0
#

from extract_utils.fixups_blob import (
    blob_fixup,
    blob_fixups_user_type,
)
from extract_utils.main import (
    ExtractUtils,
    ExtractUtilsModule,
)

blob_fixups: blob_fixups_user_type = {
    ('product/etc/permissions/vendor.qti.hardware.data.connection-V1.0-java.xml', 'product/etc/permissions/vendor.qti.hardware.data.connection-V1.1-java.xml'): blob_fixup()
        .regex_replace('version="2.0"', 'version="1.0"'),
    ('system_ext/etc/init/dpmd.rc', 'system_ext/etc/permissions/com.qti.dpmframework.xml', 'system_ext/etc/permissions/com.qualcomm.qti.imscmservice-V2.0-java.xml', 'system_ext/etc/permissions/com.qualcomm.qti.imscmservice-V2.1-java.xml', 'system_ext/etc/permissions/com.qualcomm.qti.imscmservice-V2.2-java.xml', 'system_ext/etc/permissions/dpmapi.xml', 'system_ext/etc/permissions/embms.xml', 'system_ext/etc/permissions/qcrilhook.xml', 'system_ext/etc/permissions/telephonyservice.xml'): blob_fixup()
        .regex_replace('/product/', '/system_ext/'),
    'system_ext/lib64/libdpmframework.so': blob_fixup()
        .add_needed('libcutils_shim.so'),
    'system_ext/lib64/lib-imsvideocodec.so': blob_fixup()
        .add_needed('libgui_shim.so'),
    'vendor/bin/pm-service': blob_fixup()
        .add_needed('libutils-v33.so'),
    ('vendor/etc/data/dsi_config.xml', 'vendor/etc/data/netmgr_config.xml'): blob_fixup()
        .fix_xml(),
    ('vendor/lib64/mediadrm/libwvdrmengine.so', 'vendor/lib64/libwvhidl.so'): blob_fixup()
        .add_needed('libcrypto_shim.so'),
}  # fmt: skip

module = ExtractUtilsModule(
    'msm8953-common',
    'xiaomi',
    blob_fixups=blob_fixups,
)

if __name__ == '__main__':
    utils = ExtractUtils.device(module)
    utils.run()
