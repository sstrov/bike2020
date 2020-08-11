// ParsleyConfig definition if not already set
window.ParsleyConfig = window.ParsleyConfig || {};
window.ParsleyConfig.i18n = window.ParsleyConfig.i18n || {};

// Define then the messages
window.ParsleyConfig.i18n.ko = $.extend(window.ParsleyConfig.i18n.ko || {}, {
  defaultMessage: "유효한 값이 아닙니다.",
  type: {
    email:        "이메일 형식이 아닙니다.",
    url:          "도메인 형식이 아닙니다.",
    number:       "숫자만 입력 가능합니다.",
    integer:      "정수만 입력 가능합니다.",
    digits:       "숫자만 입력 가능합니다.",
    alphanum:     "영문, 숫자만 입력 가능합니다."
  },
  notblank:       "필수항목을 입력해주세요.",
  required:       "필수항목을 입력해주세요.",
  pattern:        "유효한 값이 아닙니다.",
  min:            "%s 값보다 크거나 동일해야 합니다.",
  max:            "%s 값보다 낮거나 동일해야 합니다.",
  range:          "(%s ~ %s) 사이 값을 입력하여야 합니다.",
  minlength:      "값의 길이가 짧습니다. %s자 이상 입력하여야 합니다.",
  maxlength:      "값의 길이가 깁니다. %s자 이하 입력하여야 합니다.",
  length:         "값의 길이가 잘못되었습니다. (%s ~ %s) 사이 값을 입력하여야 합니다.",
  mincheck:       "You must select at least %s choices.",
  maxcheck:       "You must select %s choices or less.",
  check:          "You must select between %s and %s choices.",
  equalto:        "동일한 값을 입력하여야 합니다."
});

// If file is loaded after Parsley main file, auto-load locale
if ('undefined' !== typeof window.ParsleyValidator)
  window.ParsleyValidator.addCatalog('ko', window.ParsleyConfig.i18n.ko, true);
