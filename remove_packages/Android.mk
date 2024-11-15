LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := RemovePackages
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_TAGS := optional
LOCAL_OVERRIDES_PACKAGES := Aperture AudioFX Backgrounds Calendar2 Calendar Etar PicoTts
LOCAL_OVERRIDES_PACKAGES += DeviceAsWebcam Gallery2 Glimpse PhotoTable Recorder
LOCAL_OVERRIDES_PACKAGES += GoogleTTS SafetyHubPrebuilt WellbeingPrebuilt
LOCAL_OVERRIDES_PACKAGES += Photos TagGoogle talkback OdadPrebuilt
LOCAL_OVERRIDES_PACKAGES += PrebuiltBugle bcr CreativeAssistant
LOCAL_UNINSTALLABLE_MODULE := true
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_SRC_FILES := /dev/null
include $(BUILD_PREBUILT)
