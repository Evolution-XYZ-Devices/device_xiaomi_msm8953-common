LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := Remove_Packages
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_TAGS := optional
LOCAL_OVERRIDES_PACKAGES := AmbientSensePrebuilt AppDirectedSMSService
LOCAL_OVERRIDES_PACKAGES += Camera2 CarrierSetup ConnMO DCMO DMService
LOCAL_OVERRIDES_PACKAGES += DevicePolicyPrebuilt DiagnosticsToolPrebuilt
LOCAL_OVERRIDES_PACKAGES += Drive HelpRtcPrebuilt Maps MyVerizonServices
LOCAL_OVERRIDES_PACKAGES += NgaResources OBDM_Permissions OemDmTrigger
LOCAL_OVERRIDES_PACKAGES += Photos PixelLiveWallpaperPrebuilt
LOCAL_OVERRIDES_PACKAGES += PixelWallpapers2021 PrebuiltGmail
LOCAL_OVERRIDES_PACKAGES += RecorderPrebuilt SafetyHubPrebuilt
LOCAL_OVERRIDES_PACKAGES += ScribePrebuilt Showcase
LOCAL_OVERRIDES_PACKAGES += SoundAmplifierPrebuilt SprintDM SprintHM
LOCAL_OVERRIDES_PACKAGES += Tycho USCCDM VZWAPNLib VzwOmaTriger
LOCAL_OVERRIDES_PACKAGES += obdm_stub
LOCAL_UNINSTALLABLE_MODULE := true
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_SRC_FILES := /dev/null
include $(BUILD_PREBUILT)
