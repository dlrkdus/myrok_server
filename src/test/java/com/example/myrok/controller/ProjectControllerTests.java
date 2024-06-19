package com.example.myrok.controller;

import com.example.myrok.dto.record.RecordDTO;
import com.example.myrok.service.RecordService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;

@SpringBootTest
@Log4j2
public class ProjectControllerTests {
    @Autowired
    RecordService recordService;

    @Test
    public void ExpectTextTest() {
        RecordDTO recordDTO = RecordDTO.builder()
                .projectId(1L)
                .recordDate(LocalDate.now())
                .recordName("서비스 초안 기획")
                .recordContent("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><title>서비스 초안 기획 (3/11)</title><style>\n" +
                        "/* cspell:disable-file */\n" +
                        "/* webkit printing magic: print all background colors */\n" +
                        "html {\n" +
                        "\t-webkit-print-color-adjust: exact;\n" +
                        "}\n" +
                        "* {\n" +
                        "\tbox-sizing: border-box;\n" +
                        "\t-webkit-print-color-adjust: exact;\n" +
                        "}\n" +
                        "\n" +
                        "html,\n" +
                        "body {\n" +
                        "\tmargin: 0;\n" +
                        "\tpadding: 0;\n" +
                        "}\n" +
                        "@media only screen {\n" +
                        "\tbody {\n" +
                        "\t\tmargin: 2em auto;\n" +
                        "\t\tmax-width: 900px;\n" +
                        "\t\tcolor: rgb(55, 53, 47);\n" +
                        "\t}\n" +
                        "}\n" +
                        "\n" +
                        "body {\n" +
                        "\tline-height: 1.5;\n" +
                        "\twhite-space: pre-wrap;\n" +
                        "}\n" +
                        "\n" +
                        "a,\n" +
                        "a.visited {\n" +
                        "\tcolor: inherit;\n" +
                        "\ttext-decoration: underline;\n" +
                        "}\n" +
                        "\n" +
                        ".pdf-relative-link-path {\n" +
                        "\tfont-size: 80%;\n" +
                        "\tcolor: #444;\n" +
                        "}\n" +
                        "\n" +
                        "h1,\n" +
                        "h2,\n" +
                        "h3 {\n" +
                        "\tletter-spacing: -0.01em;\n" +
                        "\tline-height: 1.2;\n" +
                        "\tfont-weight: 600;\n" +
                        "\tmargin-bottom: 0;\n" +
                        "}\n" +
                        "\n" +
                        ".page-title {\n" +
                        "\tfont-size: 2.5rem;\n" +
                        "\tfont-weight: 700;\n" +
                        "\tmargin-top: 0;\n" +
                        "\tmargin-bottom: 0.75em;\n" +
                        "}\n" +
                        "\n" +
                        "h1 {\n" +
                        "\tfont-size: 1.875rem;\n" +
                        "\tmargin-top: 1.875rem;\n" +
                        "}\n" +
                        "\n" +
                        "h2 {\n" +
                        "\tfont-size: 1.5rem;\n" +
                        "\tmargin-top: 1.5rem;\n" +
                        "}\n" +
                        "\n" +
                        "h3 {\n" +
                        "\tfont-size: 1.25rem;\n" +
                        "\tmargin-top: 1.25rem;\n" +
                        "}\n" +
                        "\n" +
                        ".source {\n" +
                        "\tborder: 1px solid #ddd;\n" +
                        "\tborder-radius: 3px;\n" +
                        "\tpadding: 1.5em;\n" +
                        "\tword-break: break-all;\n" +
                        "}\n" +
                        "\n" +
                        ".callout {\n" +
                        "\tborder-radius: 3px;\n" +
                        "\tpadding: 1rem;\n" +
                        "}\n" +
                        "\n" +
                        "figure {\n" +
                        "\tmargin: 1.25em 0;\n" +
                        "\tpage-break-inside: avoid;\n" +
                        "}\n" +
                        "\n" +
                        "figcaption {\n" +
                        "\topacity: 0.5;\n" +
                        "\tfont-size: 85%;\n" +
                        "\tmargin-top: 0.5em;\n" +
                        "}\n" +
                        "\n" +
                        "mark {\n" +
                        "\tbackground-color: transparent;\n" +
                        "}\n" +
                        "\n" +
                        ".indented {\n" +
                        "\tpadding-left: 1.5em;\n" +
                        "}\n" +
                        "\n" +
                        "hr {\n" +
                        "\tbackground: transparent;\n" +
                        "\tdisplay: block;\n" +
                        "\twidth: 100%;\n" +
                        "\theight: 1px;\n" +
                        "\tvisibility: visible;\n" +
                        "\tborder: none;\n" +
                        "\tborder-bottom: 1px solid rgba(55, 53, 47, 0.09);\n" +
                        "}\n" +
                        "\n" +
                        "img {\n" +
                        "\tmax-width: 100%;\n" +
                        "}\n" +
                        "\n" +
                        "@media only print {\n" +
                        "\timg {\n" +
                        "\t\tmax-height: 100vh;\n" +
                        "\t\tobject-fit: contain;\n" +
                        "\t}\n" +
                        "}\n" +
                        "\n" +
                        "@page {\n" +
                        "\tmargin: 1in;\n" +
                        "}\n" +
                        "\n" +
                        ".collection-content {\n" +
                        "\tfont-size: 0.875rem;\n" +
                        "}\n" +
                        "\n" +
                        ".column-list {\n" +
                        "\tdisplay: flex;\n" +
                        "\tjustify-content: space-between;\n" +
                        "}\n" +
                        "\n" +
                        ".column {\n" +
                        "\tpadding: 0 1em;\n" +
                        "}\n" +
                        "\n" +
                        ".column:first-child {\n" +
                        "\tpadding-left: 0;\n" +
                        "}\n" +
                        "\n" +
                        ".column:last-child {\n" +
                        "\tpadding-right: 0;\n" +
                        "}\n" +
                        "\n" +
                        ".table_of_contents-item {\n" +
                        "\tdisplay: block;\n" +
                        "\tfont-size: 0.875rem;\n" +
                        "\tline-height: 1.3;\n" +
                        "\tpadding: 0.125rem;\n" +
                        "}\n" +
                        "\n" +
                        ".table_of_contents-indent-1 {\n" +
                        "\tmargin-left: 1.5rem;\n" +
                        "}\n" +
                        "\n" +
                        ".table_of_contents-indent-2 {\n" +
                        "\tmargin-left: 3rem;\n" +
                        "}\n" +
                        "\n" +
                        ".table_of_contents-indent-3 {\n" +
                        "\tmargin-left: 4.5rem;\n" +
                        "}\n" +
                        "\n" +
                        ".table_of_contents-link {\n" +
                        "\ttext-decoration: none;\n" +
                        "\topacity: 0.7;\n" +
                        "\tborder-bottom: 1px solid rgba(55, 53, 47, 0.18);\n" +
                        "}\n" +
                        "\n" +
                        "table,\n" +
                        "th,\n" +
                        "td {\n" +
                        "\tborder: 1px solid rgba(55, 53, 47, 0.09);\n" +
                        "\tborder-collapse: collapse;\n" +
                        "}\n" +
                        "\n" +
                        "table {\n" +
                        "\tborder-left: none;\n" +
                        "\tborder-right: none;\n" +
                        "}\n" +
                        "\n" +
                        "th,\n" +
                        "td {\n" +
                        "\tfont-weight: normal;\n" +
                        "\tpadding: 0.25em 0.5em;\n" +
                        "\tline-height: 1.5;\n" +
                        "\tmin-height: 1.5em;\n" +
                        "\ttext-align: left;\n" +
                        "}\n" +
                        "\n" +
                        "th {\n" +
                        "\tcolor: rgba(55, 53, 47, 0.6);\n" +
                        "}\n" +
                        "\n" +
                        "ol,\n" +
                        "ul {\n" +
                        "\tmargin: 0;\n" +
                        "\tmargin-block-start: 0.6em;\n" +
                        "\tmargin-block-end: 0.6em;\n" +
                        "}\n" +
                        "\n" +
                        "li > ol:first-child,\n" +
                        "li > ul:first-child {\n" +
                        "\tmargin-block-start: 0.6em;\n" +
                        "}\n" +
                        "\n" +
                        "ul > li {\n" +
                        "\tlist-style: disc;\n" +
                        "}\n" +
                        "\n" +
                        "ul.to-do-list {\n" +
                        "\tpadding-inline-start: 0;\n" +
                        "}\n" +
                        "\n" +
                        "ul.to-do-list > li {\n" +
                        "\tlist-style: none;\n" +
                        "}\n" +
                        "\n" +
                        ".to-do-children-checked {\n" +
                        "\ttext-decoration: line-through;\n" +
                        "\topacity: 0.375;\n" +
                        "}\n" +
                        "\n" +
                        "ul.toggle > li {\n" +
                        "\tlist-style: none;\n" +
                        "}\n" +
                        "\n" +
                        "ul {\n" +
                        "\tpadding-inline-start: 1.7em;\n" +
                        "}\n" +
                        "\n" +
                        "ul > li {\n" +
                        "\tpadding-left: 0.1em;\n" +
                        "}\n" +
                        "\n" +
                        "ol {\n" +
                        "\tpadding-inline-start: 1.6em;\n" +
                        "}\n" +
                        "\n" +
                        "ol > li {\n" +
                        "\tpadding-left: 0.2em;\n" +
                        "}\n" +
                        "\n" +
                        ".mono ol {\n" +
                        "\tpadding-inline-start: 2em;\n" +
                        "}\n" +
                        "\n" +
                        ".mono ol > li {\n" +
                        "\ttext-indent: -0.4em;\n" +
                        "}\n" +
                        "\n" +
                        ".toggle {\n" +
                        "\tpadding-inline-start: 0em;\n" +
                        "\tlist-style-type: none;\n" +
                        "}\n" +
                        "\n" +
                        "/* Indent toggle children */\n" +
                        ".toggle > li > details {\n" +
                        "\tpadding-left: 1.7em;\n" +
                        "}\n" +
                        "\n" +
                        ".toggle > li > details > summary {\n" +
                        "\tmargin-left: -1.1em;\n" +
                        "}\n" +
                        "\n" +
                        ".selected-value {\n" +
                        "\tdisplay: inline-block;\n" +
                        "\tpadding: 0 0.5em;\n" +
                        "\tbackground: rgba(206, 205, 202, 0.5);\n" +
                        "\tborder-radius: 3px;\n" +
                        "\tmargin-right: 0.5em;\n" +
                        "\tmargin-top: 0.3em;\n" +
                        "\tmargin-bottom: 0.3em;\n" +
                        "\twhite-space: nowrap;\n" +
                        "}\n" +
                        "\n" +
                        ".collection-title {\n" +
                        "\tdisplay: inline-block;\n" +
                        "\tmargin-right: 1em;\n" +
                        "}\n" +
                        "\n" +
                        ".page-description {\n" +
                        "    margin-bottom: 2em;\n" +
                        "}\n" +
                        "\n" +
                        ".simple-table {\n" +
                        "\tmargin-top: 1em;\n" +
                        "\tfont-size: 0.875rem;\n" +
                        "\tempty-cells: show;\n" +
                        "}\n" +
                        ".simple-table td {\n" +
                        "\theight: 29px;\n" +
                        "\tmin-width: 120px;\n" +
                        "}\n" +
                        "\n" +
                        ".simple-table th {\n" +
                        "\theight: 29px;\n" +
                        "\tmin-width: 120px;\n" +
                        "}\n" +
                        "\n" +
                        ".simple-table-header-color {\n" +
                        "\tbackground: rgb(247, 246, 243);\n" +
                        "\tcolor: black;\n" +
                        "}\n" +
                        ".simple-table-header {\n" +
                        "\tfont-weight: 500;\n" +
                        "}\n" +
                        "\n" +
                        "time {\n" +
                        "\topacity: 0.5;\n" +
                        "}\n" +
                        "\n" +
                        ".icon {\n" +
                        "\tdisplay: inline-block;\n" +
                        "\tmax-width: 1.2em;\n" +
                        "\tmax-height: 1.2em;\n" +
                        "\ttext-decoration: none;\n" +
                        "\tvertical-align: text-bottom;\n" +
                        "\tmargin-right: 0.5em;\n" +
                        "}\n" +
                        "\n" +
                        "img.icon {\n" +
                        "\tborder-radius: 3px;\n" +
                        "}\n" +
                        "\n" +
                        ".user-icon {\n" +
                        "\twidth: 1.5em;\n" +
                        "\theight: 1.5em;\n" +
                        "\tborder-radius: 100%;\n" +
                        "\tmargin-right: 0.5rem;\n" +
                        "}\n" +
                        "\n" +
                        ".user-icon-inner {\n" +
                        "\tfont-size: 0.8em;\n" +
                        "}\n" +
                        "\n" +
                        ".text-icon {\n" +
                        "\tborder: 1px solid #000;\n" +
                        "\ttext-align: center;\n" +
                        "}\n" +
                        "\n" +
                        ".page-cover-image {\n" +
                        "\tdisplay: block;\n" +
                        "\tobject-fit: cover;\n" +
                        "\twidth: 100%;\n" +
                        "\tmax-height: 30vh;\n" +
                        "}\n" +
                        "\n" +
                        ".page-header-icon {\n" +
                        "\tfont-size: 3rem;\n" +
                        "\tmargin-bottom: 1rem;\n" +
                        "}\n" +
                        "\n" +
                        ".page-header-icon-with-cover {\n" +
                        "\tmargin-top: -0.72em;\n" +
                        "\tmargin-left: 0.07em;\n" +
                        "}\n" +
                        "\n" +
                        ".page-header-icon img {\n" +
                        "\tborder-radius: 3px;\n" +
                        "}\n" +
                        "\n" +
                        ".link-to-page {\n" +
                        "\tmargin: 1em 0;\n" +
                        "\tpadding: 0;\n" +
                        "\tborder: none;\n" +
                        "\tfont-weight: 500;\n" +
                        "}\n" +
                        "\n" +
                        "p > .user {\n" +
                        "\topacity: 0.5;\n" +
                        "}\n" +
                        "\n" +
                        "td > .user,\n" +
                        "td > time {\n" +
                        "\twhite-space: nowrap;\n" +
                        "}\n" +
                        "\n" +
                        "input[type=\"checkbox\"] {\n" +
                        "\ttransform: scale(1.5);\n" +
                        "\tmargin-right: 0.6em;\n" +
                        "\tvertical-align: middle;\n" +
                        "}\n" +
                        "\n" +
                        "p {\n" +
                        "\tmargin-top: 0.5em;\n" +
                        "\tmargin-bottom: 0.5em;\n" +
                        "}\n" +
                        "\n" +
                        ".image {\n" +
                        "\tborder: none;\n" +
                        "\tmargin: 1.5em 0;\n" +
                        "\tpadding: 0;\n" +
                        "\tborder-radius: 0;\n" +
                        "\ttext-align: center;\n" +
                        "}\n" +
                        "\n" +
                        ".code,\n" +
                        "code {\n" +
                        "\tbackground: rgba(135, 131, 120, 0.15);\n" +
                        "\tborder-radius: 3px;\n" +
                        "\tpadding: 0.2em 0.4em;\n" +
                        "\tborder-radius: 3px;\n" +
                        "\tfont-size: 85%;\n" +
                        "\ttab-size: 2;\n" +
                        "}\n" +
                        "\n" +
                        "code {\n" +
                        "\tcolor: #eb5757;\n" +
                        "}\n" +
                        "\n" +
                        ".code {\n" +
                        "\tpadding: 1.5em 1em;\n" +
                        "}\n" +
                        "\n" +
                        ".code-wrap {\n" +
                        "\twhite-space: pre-wrap;\n" +
                        "\tword-break: break-all;\n" +
                        "}\n" +
                        "\n" +
                        ".code > code {\n" +
                        "\tbackground: none;\n" +
                        "\tpadding: 0;\n" +
                        "\tfont-size: 100%;\n" +
                        "\tcolor: inherit;\n" +
                        "}\n" +
                        "\n" +
                        "blockquote {\n" +
                        "\tfont-size: 1.25em;\n" +
                        "\tmargin: 1em 0;\n" +
                        "\tpadding-left: 1em;\n" +
                        "\tborder-left: 3px solid rgb(55, 53, 47);\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark {\n" +
                        "\ttext-decoration: none;\n" +
                        "\tmax-height: 8em;\n" +
                        "\tpadding: 0;\n" +
                        "\tdisplay: flex;\n" +
                        "\twidth: 100%;\n" +
                        "\talign-items: stretch;\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark-title {\n" +
                        "\tfont-size: 0.85em;\n" +
                        "\toverflow: hidden;\n" +
                        "\ttext-overflow: ellipsis;\n" +
                        "\theight: 1.75em;\n" +
                        "\twhite-space: nowrap;\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark-text {\n" +
                        "\tdisplay: flex;\n" +
                        "\tflex-direction: column;\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark-info {\n" +
                        "\tflex: 4 1 180px;\n" +
                        "\tpadding: 12px 14px 14px;\n" +
                        "\tdisplay: flex;\n" +
                        "\tflex-direction: column;\n" +
                        "\tjustify-content: space-between;\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark-image {\n" +
                        "\twidth: 33%;\n" +
                        "\tflex: 1 1 180px;\n" +
                        "\tdisplay: block;\n" +
                        "\tposition: relative;\n" +
                        "\tobject-fit: cover;\n" +
                        "\tborder-radius: 1px;\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark-description {\n" +
                        "\tcolor: rgba(55, 53, 47, 0.6);\n" +
                        "\tfont-size: 0.75em;\n" +
                        "\toverflow: hidden;\n" +
                        "\tmax-height: 4.5em;\n" +
                        "\tword-break: break-word;\n" +
                        "}\n" +
                        "\n" +
                        ".bookmark-href {\n" +
                        "\tfont-size: 0.75em;\n" +
                        "\tmargin-top: 0.25em;\n" +
                        "}\n" +
                        "\n" +
                        ".sans { font-family: ui-sans-serif, -apple-system, BlinkMacSystemFont, \"Segoe UI Variable Display\", \"Segoe UI\", Helvetica, \"Apple Color Emoji\", Arial, sans-serif, \"Segoe UI Emoji\", \"Segoe UI Symbol\"; }\n" +
                        ".code { font-family: \"SFMono-Regular\", Menlo, Consolas, \"PT Mono\", \"Liberation Mono\", Courier, monospace; }\n" +
                        ".serif { font-family: Lyon-Text, Georgia, ui-serif, serif; }\n" +
                        ".mono { font-family: iawriter-mono, Nitti, Menlo, Courier, monospace; }\n" +
                        ".pdf .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, \"Segoe UI Variable Display\", \"Segoe UI\", Helvetica, \"Apple Color Emoji\", Arial, sans-serif, \"Segoe UI Emoji\", \"Segoe UI Symbol\", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK JP'; }\n" +
                        ".pdf:lang(zh-CN) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, \"Segoe UI Variable Display\", \"Segoe UI\", Helvetica, \"Apple Color Emoji\", Arial, sans-serif, \"Segoe UI Emoji\", \"Segoe UI Symbol\", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK SC'; }\n" +
                        ".pdf:lang(zh-TW) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, \"Segoe UI Variable Display\", \"Segoe UI\", Helvetica, \"Apple Color Emoji\", Arial, sans-serif, \"Segoe UI Emoji\", \"Segoe UI Symbol\", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK TC'; }\n" +
                        ".pdf:lang(ko-KR) .sans { font-family: Inter, ui-sans-serif, -apple-system, BlinkMacSystemFont, \"Segoe UI Variable Display\", \"Segoe UI\", Helvetica, \"Apple Color Emoji\", Arial, sans-serif, \"Segoe UI Emoji\", \"Segoe UI Symbol\", 'Twemoji', 'Noto Color Emoji', 'Noto Sans CJK KR'; }\n" +
                        ".pdf .code { font-family: Source Code Pro, \"SFMono-Regular\", Menlo, Consolas, \"PT Mono\", \"Liberation Mono\", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }\n" +
                        ".pdf:lang(zh-CN) .code { font-family: Source Code Pro, \"SFMono-Regular\", Menlo, Consolas, \"PT Mono\", \"Liberation Mono\", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }\n" +
                        ".pdf:lang(zh-TW) .code { font-family: Source Code Pro, \"SFMono-Regular\", Menlo, Consolas, \"PT Mono\", \"Liberation Mono\", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }\n" +
                        ".pdf:lang(ko-KR) .code { font-family: Source Code Pro, \"SFMono-Regular\", Menlo, Consolas, \"PT Mono\", \"Liberation Mono\", Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }\n" +
                        ".pdf .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK JP'; }\n" +
                        ".pdf:lang(zh-CN) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK SC'; }\n" +
                        ".pdf:lang(zh-TW) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK TC'; }\n" +
                        ".pdf:lang(ko-KR) .serif { font-family: PT Serif, Lyon-Text, Georgia, ui-serif, serif, 'Twemoji', 'Noto Color Emoji', 'Noto Serif CJK KR'; }\n" +
                        ".pdf .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK JP'; }\n" +
                        ".pdf:lang(zh-CN) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK SC'; }\n" +
                        ".pdf:lang(zh-TW) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK TC'; }\n" +
                        ".pdf:lang(ko-KR) .mono { font-family: PT Mono, iawriter-mono, Nitti, Menlo, Courier, monospace, 'Twemoji', 'Noto Color Emoji', 'Noto Sans Mono CJK KR'; }\n" +
                        ".highlight-default {\n" +
                        "\tcolor: rgba(55, 53, 47, 1);\n" +
                        "}\n" +
                        ".highlight-gray {\n" +
                        "\tcolor: rgba(120, 119, 116, 1);\n" +
                        "\tfill: rgba(120, 119, 116, 1);\n" +
                        "}\n" +
                        ".highlight-brown {\n" +
                        "\tcolor: rgba(159, 107, 83, 1);\n" +
                        "\tfill: rgba(159, 107, 83, 1);\n" +
                        "}\n" +
                        ".highlight-orange {\n" +
                        "\tcolor: rgba(217, 115, 13, 1);\n" +
                        "\tfill: rgba(217, 115, 13, 1);\n" +
                        "}\n" +
                        ".highlight-yellow {\n" +
                        "\tcolor: rgba(203, 145, 47, 1);\n" +
                        "\tfill: rgba(203, 145, 47, 1);\n" +
                        "}\n" +
                        ".highlight-teal {\n" +
                        "\tcolor: rgba(68, 131, 97, 1);\n" +
                        "\tfill: rgba(68, 131, 97, 1);\n" +
                        "}\n" +
                        ".highlight-blue {\n" +
                        "\tcolor: rgba(51, 126, 169, 1);\n" +
                        "\tfill: rgba(51, 126, 169, 1);\n" +
                        "}\n" +
                        ".highlight-purple {\n" +
                        "\tcolor: rgba(144, 101, 176, 1);\n" +
                        "\tfill: rgba(144, 101, 176, 1);\n" +
                        "}\n" +
                        ".highlight-pink {\n" +
                        "\tcolor: rgba(193, 76, 138, 1);\n" +
                        "\tfill: rgba(193, 76, 138, 1);\n" +
                        "}\n" +
                        ".highlight-red {\n" +
                        "\tcolor: rgba(212, 76, 71, 1);\n" +
                        "\tfill: rgba(212, 76, 71, 1);\n" +
                        "}\n" +
                        ".highlight-gray_background {\n" +
                        "\tbackground: rgba(241, 241, 239, 1);\n" +
                        "}\n" +
                        ".highlight-brown_background {\n" +
                        "\tbackground: rgba(244, 238, 238, 1);\n" +
                        "}\n" +
                        ".highlight-orange_background {\n" +
                        "\tbackground: rgba(251, 236, 221, 1);\n" +
                        "}\n" +
                        ".highlight-yellow_background {\n" +
                        "\tbackground: rgba(251, 243, 219, 1);\n" +
                        "}\n" +
                        ".highlight-teal_background {\n" +
                        "\tbackground: rgba(237, 243, 236, 1);\n" +
                        "}\n" +
                        ".highlight-blue_background {\n" +
                        "\tbackground: rgba(231, 243, 248, 1);\n" +
                        "}\n" +
                        ".highlight-purple_background {\n" +
                        "\tbackground: rgba(244, 240, 247, 0.8);\n" +
                        "}\n" +
                        ".highlight-pink_background {\n" +
                        "\tbackground: rgba(249, 238, 243, 0.8);\n" +
                        "}\n" +
                        ".highlight-red_background {\n" +
                        "\tbackground: rgba(253, 235, 236, 1);\n" +
                        "}\n" +
                        ".block-color-default {\n" +
                        "\tcolor: inherit;\n" +
                        "\tfill: inherit;\n" +
                        "}\n" +
                        ".block-color-gray {\n" +
                        "\tcolor: rgba(120, 119, 116, 1);\n" +
                        "\tfill: rgba(120, 119, 116, 1);\n" +
                        "}\n" +
                        ".block-color-brown {\n" +
                        "\tcolor: rgba(159, 107, 83, 1);\n" +
                        "\tfill: rgba(159, 107, 83, 1);\n" +
                        "}\n" +
                        ".block-color-orange {\n" +
                        "\tcolor: rgba(217, 115, 13, 1);\n" +
                        "\tfill: rgba(217, 115, 13, 1);\n" +
                        "}\n" +
                        ".block-color-yellow {\n" +
                        "\tcolor: rgba(203, 145, 47, 1);\n" +
                        "\tfill: rgba(203, 145, 47, 1);\n" +
                        "}\n" +
                        ".block-color-teal {\n" +
                        "\tcolor: rgba(68, 131, 97, 1);\n" +
                        "\tfill: rgba(68, 131, 97, 1);\n" +
                        "}\n" +
                        ".block-color-blue {\n" +
                        "\tcolor: rgba(51, 126, 169, 1);\n" +
                        "\tfill: rgba(51, 126, 169, 1);\n" +
                        "}\n" +
                        ".block-color-purple {\n" +
                        "\tcolor: rgba(144, 101, 176, 1);\n" +
                        "\tfill: rgba(144, 101, 176, 1);\n" +
                        "}\n" +
                        ".block-color-pink {\n" +
                        "\tcolor: rgba(193, 76, 138, 1);\n" +
                        "\tfill: rgba(193, 76, 138, 1);\n" +
                        "}\n" +
                        ".block-color-red {\n" +
                        "\tcolor: rgba(212, 76, 71, 1);\n" +
                        "\tfill: rgba(212, 76, 71, 1);\n" +
                        "}\n" +
                        ".block-color-gray_background {\n" +
                        "\tbackground: rgba(241, 241, 239, 1);\n" +
                        "}\n" +
                        ".block-color-brown_background {\n" +
                        "\tbackground: rgba(244, 238, 238, 1);\n" +
                        "}\n" +
                        ".block-color-orange_background {\n" +
                        "\tbackground: rgba(251, 236, 221, 1);\n" +
                        "}\n" +
                        ".block-color-yellow_background {\n" +
                        "\tbackground: rgba(251, 243, 219, 1);\n" +
                        "}\n" +
                        ".block-color-teal_background {\n" +
                        "\tbackground: rgba(237, 243, 236, 1);\n" +
                        "}\n" +
                        ".block-color-blue_background {\n" +
                        "\tbackground: rgba(231, 243, 248, 1);\n" +
                        "}\n" +
                        ".block-color-purple_background {\n" +
                        "\tbackground: rgba(244, 240, 247, 0.8);\n" +
                        "}\n" +
                        ".block-color-pink_background {\n" +
                        "\tbackground: rgba(249, 238, 243, 0.8);\n" +
                        "}\n" +
                        ".block-color-red_background {\n" +
                        "\tbackground: rgba(253, 235, 236, 1);\n" +
                        "}\n" +
                        ".select-value-color-uiBlue { background-color: rgba(35, 131, 226, .07); }\n" +
                        ".select-value-color-pink { background-color: rgba(245, 224, 233, 1); }\n" +
                        ".select-value-color-purple { background-color: rgba(232, 222, 238, 1); }\n" +
                        ".select-value-color-green { background-color: rgba(219, 237, 219, 1); }\n" +
                        ".select-value-color-gray { background-color: rgba(227, 226, 224, 1); }\n" +
                        ".select-value-color-transparentGray { background-color: rgba(227, 226, 224, 0); }\n" +
                        ".select-value-color-translucentGray { background-color: rgba(255, 255, 255, 0.0375); }\n" +
                        ".select-value-color-orange { background-color: rgba(250, 222, 201, 1); }\n" +
                        ".select-value-color-brown { background-color: rgba(238, 224, 218, 1); }\n" +
                        ".select-value-color-red { background-color: rgba(255, 226, 221, 1); }\n" +
                        ".select-value-color-yellow { background-color: rgba(253, 236, 200, 1); }\n" +
                        ".select-value-color-blue { background-color: rgba(211, 229, 239, 1); }\n" +
                        ".select-value-color-pageGlass { background-color: undefined; }\n" +
                        ".select-value-color-washGlass { background-color: undefined; }\n" +
                        "\n" +
                        ".checkbox {\n" +
                        "\tdisplay: inline-flex;\n" +
                        "\tvertical-align: text-bottom;\n" +
                        "\twidth: 16;\n" +
                        "\theight: 16;\n" +
                        "\tbackground-size: 16px;\n" +
                        "\tmargin-left: 2px;\n" +
                        "\tmargin-right: 5px;\n" +
                        "}\n" +
                        "\n" +
                        ".checkbox-on {\n" +
                        "\tbackground-image: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20width%3D%2216%22%20height%3D%2216%22%20fill%3D%22%2358A9D7%22%2F%3E%0A%3Cpath%20d%3D%22M6.71429%2012.2852L14%204.9995L12.7143%203.71436L6.71429%209.71378L3.28571%206.2831L2%207.57092L6.71429%2012.2852Z%22%20fill%3D%22white%22%2F%3E%0A%3C%2Fsvg%3E\");\n" +
                        "}\n" +
                        "\n" +
                        ".checkbox-off {\n" +
                        "\tbackground-image: url(\"data:image/svg+xml;charset=UTF-8,%3Csvg%20width%3D%2216%22%20height%3D%2216%22%20viewBox%3D%220%200%2016%2016%22%20fill%3D%22none%22%20xmlns%3D%22http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg%22%3E%0A%3Crect%20x%3D%220.75%22%20y%3D%220.75%22%20width%3D%2214.5%22%20height%3D%2214.5%22%20fill%3D%22white%22%20stroke%3D%22%2336352F%22%20stroke-width%3D%221.5%22%2F%3E%0A%3C%2Fsvg%3E\");\n" +
                        "}\n" +
                        "\t\n" +
                        "</style></head><body><article id=\"3a37bf86-51a6-4ab3-b7ba-e605b29c6fdf\" class=\"page sans\"><header><div class=\"page-header-icon undefined\"><span class=\"icon\">\uD83D\uDCBC</span></div><h1 class=\"page-title\">서비스 초안 기획 (3/11)</h1><p class=\"page-description\"></p><table class=\"properties\"><tbody><tr class=\"property-row property-row-multi_select\"><th><span class=\"icon property-icon\"><svg role=\"graphics-symbol\" viewBox=\"0 0 16 16\" style=\"width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0\" class=\"typesMultipleSelect\"><path d=\"M1.91602 4.83789C2.44238 4.83789 2.87305 4.40723 2.87305 3.87402C2.87305 3.34766 2.44238 2.91699 1.91602 2.91699C1.38281 2.91699 0.952148 3.34766 0.952148 3.87402C0.952148 4.40723 1.38281 4.83789 1.91602 4.83789ZM5.1084 4.52344H14.3984C14.7607 4.52344 15.0479 4.23633 15.0479 3.87402C15.0479 3.51172 14.7607 3.22461 14.3984 3.22461H5.1084C4.74609 3.22461 4.45898 3.51172 4.45898 3.87402C4.45898 4.23633 4.74609 4.52344 5.1084 4.52344ZM1.91602 9.03516C2.44238 9.03516 2.87305 8.60449 2.87305 8.07129C2.87305 7.54492 2.44238 7.11426 1.91602 7.11426C1.38281 7.11426 0.952148 7.54492 0.952148 8.07129C0.952148 8.60449 1.38281 9.03516 1.91602 9.03516ZM5.1084 8.7207H14.3984C14.7607 8.7207 15.0479 8.43359 15.0479 8.07129C15.0479 7.70898 14.7607 7.42188 14.3984 7.42188H5.1084C4.74609 7.42188 4.45898 7.70898 4.45898 8.07129C4.45898 8.43359 4.74609 8.7207 5.1084 8.7207ZM1.91602 13.2324C2.44238 13.2324 2.87305 12.8018 2.87305 12.2686C2.87305 11.7422 2.44238 11.3115 1.91602 11.3115C1.38281 11.3115 0.952148 11.7422 0.952148 12.2686C0.952148 12.8018 1.38281 13.2324 1.91602 13.2324ZM5.1084 12.918H14.3984C14.7607 12.918 15.0479 12.6309 15.0479 12.2686C15.0479 11.9062 14.7607 11.6191 14.3984 11.6191H5.1084C4.74609 11.6191 4.45898 11.9062 4.45898 12.2686C4.45898 12.6309 4.74609 12.918 5.1084 12.918Z\"></path></svg></span>태그</th><td></td></tr><tr class=\"property-row property-row-created_time\"><th><span class=\"icon property-icon\"><svg role=\"graphics-symbol\" viewBox=\"0 0 16 16\" style=\"width:14px;height:14px;display:block;fill:rgba(55, 53, 47, 0.45);flex-shrink:0\" class=\"typesCreatedAt\"><path d=\"M8 15.126C11.8623 15.126 15.0615 11.9336 15.0615 8.06445C15.0615 4.20215 11.8623 1.00293 7.99316 1.00293C4.13086 1.00293 0.938477 4.20215 0.938477 8.06445C0.938477 11.9336 4.1377 15.126 8 15.126ZM8 13.7383C4.85547 13.7383 2.33301 11.209 2.33301 8.06445C2.33301 4.91992 4.84863 2.39746 7.99316 2.39746C11.1377 2.39746 13.6738 4.91992 13.6738 8.06445C13.6738 11.209 11.1445 13.7383 8 13.7383ZM4.54102 8.91211H7.99316C8.30078 8.91211 8.54004 8.67285 8.54004 8.37207V3.8877C8.54004 3.58691 8.30078 3.34766 7.99316 3.34766C7.69238 3.34766 7.45312 3.58691 7.45312 3.8877V7.83203H4.54102C4.2334 7.83203 4.00098 8.06445 4.00098 8.37207C4.00098 8.67285 4.2334 8.91211 4.54102 8.91211Z\"></path></svg></span>생성 일시</th><td><time>@2024년 3월 11일 오후 11:45</time></td></tr></tbody></table></header><div class=\"page-body\"><h1 id=\"6b962b14-cd24-4689-ba0a-ac30feda92f8\" class=\"\">서비스 기획 제안</h1><p id=\"c425f663-ffb0-4adc-b411-bf2fb3672afb\" class=\"\">김도연- 축제 팟 구하기<br>송지은- 코드리뷰 사이트(채팅,에디터 포함)<br>백승지- 협업툴(AI)<br>이가연- 협업툴(채팅/음성채팅 포함)<br></p><p id=\"6049bf9d-b904-44ea-847b-4dd12d04e8f6\" class=\"\">\n" +
                        "</p><h1 id=\"4d360ea5-95ad-471f-b694-b02324569914\" class=\"\">필수 구현</h1><p id=\"459ede2f-c0b1-427d-ad6d-5e33b7517b2b\" class=\"\">소셜 로그인/Spring Security<br>웹소켓 실시간 채팅<br></p><p id=\"8b74cfc0-0adb-469b-92fc-e21b3124c324\" class=\"\">\n" +
                        "</p><h1 id=\"9eb5085e-c3e3-4cda-96a4-5e1e4923fd79\" class=\"\">하고 싶은 구현</h1><p id=\"84035fe3-5880-4528-8762-811869a0a7b5\" class=\"\">AI<br>음성 채팅<br>에디터<br></p><p id=\"720ffb20-29b9-461d-93ac-98c37371a81b\" class=\"\">협업툴 -&gt; UI 쪽에서 일이 많아질듯<br>코드리뷰 -&gt; 프론트쪽에서는 구현이 ㄱㅊ을거 같지만 백엔드가 좀 단순해질 수 있다<br></p><p id=\"98eccb0e-cab0-4b15-9568-148029b620ba\" class=\"\">협업툴, 일정, 팀 전환<br>AI가 회의 내용 요약해줌<br></p><p id=\"563bdeba-5fcc-4b75-925a-58dd0ade2c0d\" class=\"\">\n" +
                        "</p><h1 id=\"25eaeb12-a2aa-4c25-8ab1-9eb1f1db34fe\" class=\"\">기획 틀</h1><ol type=\"1\" id=\"31dabe02-6818-4eec-a21f-d1e7461ebcc5\" class=\"numbered-list\" start=\"1\"><li>팀 만들기 (초대코드, 링크, 친구 초대)</li></ol><ol type=\"1\" id=\"7f16640e-e506-4896-93b5-8defd0ab2e16\" class=\"numbered-list\" start=\"2\"><li>팀별로 프로젝트 관리 (팀별로 프로젝트는 1개가 최대)</li></ol><ol type=\"1\" id=\"f84cd299-03d0-4ac0-8409-8c67b4728a0d\" class=\"numbered-list\" start=\"3\"><li>첫번째로 프로젝트 기획,구상 단계 -&gt; 일정, 기능 등 추가 (이게 회의록 목록)</li></ol><ol type=\"1\" id=\"c6d47c2b-0229-489f-a38b-7b85887d84a4\" class=\"numbered-list\" start=\"4\"><li>회의록 기준으로 버스형 일정표 생성<br>-&gt; AI회의 요약일 수도 있고 작성 기반일 수도 있고 ,,,<br></li></ol><ol type=\"1\" id=\"204aedd9-43cd-4df2-be0a-0fa7edd59f2b\" class=\"numbered-list\" start=\"5\"><li>일정표의 동그라미 누르면 CRUD 형태의 회의칸이 뜸</li></ol><ol type=\"1\" id=\"dafe5568-9beb-4454-aee6-b00aa0e85452\" class=\"numbered-list\" start=\"6\"><li>회의 기록에 따라 잔디 심기 (진척도 볼 수 있음)</li></ol><h2 id=\"ec95b4d3-e85e-4d82-8392-7a8b82ad61cc\" class=\"\">AI 관련</h2><ul id=\"4e91c0fa-735f-4245-b6a3-dd38aeda66e4\" class=\"bulleted-list\"><li style=\"list-style-type:disc\">텍스트 채팅 요약 (첨부파일 등을 크롤링해서 텍스트로 전환해 미니 챗지피티가 내장)</li></ul><ul id=\"c5209367-a6f0-4625-87d9-36005744a851\" class=\"bulleted-list\"><li style=\"list-style-type:disc\">회의록 요약</li></ul><p id=\"ab4883df-3452-457d-a859-8f39e097b6e8\" class=\"\">\n" +
                        "</p><h1 id=\"047a16f8-02e2-4f20-ac47-7ecf500428b3\" class=\"\">다음 회의까지 해야하는 일</h1><p id=\"fe8b6ef7-2161-4169-ab0a-dc4a51302a99\" class=\"\">간단하게 레퍼런스할 정도의 사례 알아보긴</p><p id=\"9f68af53-d737-4c79-954b-2deef788fbb0\" class=\"\">\n" +
                        "</p></div></article><span class=\"sans\" style=\"font-size:14px;padding-top:2em\"></span></body></html>")
                .memberList(Arrays.asList(1L))
                .recordWriterId(1L)
                .tagList(Arrays.asList("초안", "기획", "초기"))
                        .build();
        recordService.save(recordDTO);
    }
}
