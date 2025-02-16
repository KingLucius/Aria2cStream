package com.lagradost.fetchbutton.aria2c

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.lagradost.fetchbutton.NotificationMetaData

enum class DownloadStatus {
    Started,
    Paused,
    Stopped,
    Completed,
    Error,
    CompletedAndStartSeed,
}

enum class DownloadStatusTell {
    /**currently downloading/seeding downloads*/
    @JsonProperty("active")
    Active,

    /**for downloads in the queue; download is not started*/
    @JsonProperty("waiting")
    Waiting,

    /**for paused downloads*/
    @JsonProperty("paused")
    Paused,

    /**for downloads that were stopped because of error*/
    @JsonProperty("error")
    Error,

    /**for stopped and completed downloads*/
    @JsonProperty("complete")
    Complete,

    /**for the downloads removed by user*/
    @JsonProperty("removed")
    Removed,
}

/** Ported from https://aria2.github.io/manual/en/html/aria2c.html */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class Aria2Args(
    /** --dir, The directory to store the downloaded file.*/
    @JsonProperty("dir")
    val dir: String? = null,

    /**
     * -i, --input-file=<FILE>
     *
     * Downloads the URIs listed in FILE.
     * You can specify multiple sources for a single entity by putting multiple URIs on a single line
     * separated by the TAB character. Additionally, options can be specified after each URI line.
     * Option lines must start with one or more white space characters (SPACE or TAB) and must only
     * contain one option per line. Input files can use gzip compression.
     * When FILE is specified as -, aria2 will read the input from stdin. See the Input File
     * subsection for details.
     *
     * See also the --deferred-input option. See also the --save-session option.
     */
    @JsonProperty("input-file")
    val inputFile: String? = null,

    /**
     * The file name of the log file. If - is specified, log is written to stdout.
     * If empty string("") is specified, or this option is omitted, no log is written to disk at all.
     */
    @JsonProperty("log")
    val log: String? = null,


    /**
     *  -j, --max-concurrent-downloads=<N>
     * Set the maximum number of parallel downloads for every queue item.
     * See also the --split option.
     * Default: 5
     */
    @JsonProperty("max-concurrent-downloads")
    val maxConcurrentDownloads: Int? = null,

    /** --check-integrity,
     *
     * Check file integrity by validating piece hashes or a hash of entire file.
     * This option has effect only in BitTorrent, Metalink downloads with checksums or HTTP(S)/FTP
     * downloads with --checksum option. If piece hashes are provided, this option can detect damaged
     * portions of a file and re-download them. If a hash of entire file is provided, hash check is
     * only done when file has been already download. This is determined by file length.
     * If hash check fails, file is re-downloaded from scratch. If both piece hashes and a hash
     * of entire file are provided, only piece hashes are used.
     * Default: false
     * */
    @JsonProperty("check-integrity")
    val checkIntegrity: Boolean? = null,

    /**
     * --continue,
     *
     * Continue downloading a partially downloaded file. Use this option to resume a
     * download started by a web browser or another program which downloads files sequentially
     * from the beginning. Currently this option is only applicable to HTTP(S)/FTP downloads.
     * */
    @JsonProperty("continue")
    val continueDownload: Boolean? = null,

    /**
     * The help messages are classified with tags.
     * A tag starts with #.
     * For example, type --help=#http to get the usage for the options tagged with #http.
     * If non-tag word is given, print the usage for the options whose name includes that word.
     * Available Values: #basic, #advanced, #http, #https, #ftp, #metalink, #bittorrent, #cookie,
     * #hook, #file, #rpc, #checksum, #experimental, #deprecated, #help, #all
     */
    @JsonProperty("help")
    val help: HelpType? = null,

    /**
     *
     * --all-proxy=<PROXY>
     *
     *     Use a proxy server for all protocols. To override a previously defined proxy, use "".
     *     You also can override this setting and specify a proxy server for a particular protocol using
     *     --http-proxy, --https-proxy and --ftp-proxy options. This affects all downloads.
     *     The format of PROXY is [http://][USER:PASSWORD@]HOST[:PORT]. See also ENVIRONMENT section.
     *
     *     Note
     *
     *     If user and password are embedded in proxy URI and they are also specified by
     *     --{http,https,ftp,all}-proxy-{user,passwd} options, those specified later override prior options.
     *     For example, if you specified http-proxy-user=myname, http-proxy-passwd=mypass in aria2.conf
     *     and you specified --http-proxy="http://proxy" on the command-line, then you'd get HTTP proxy
     *     http://proxy with user myname and password mypass.
     *
     *     Another example: if you specified on the command-line --http-proxy="http://user:pass@proxy"
     *     --http-proxy-user="myname" --http-proxy-passwd="mypass", then you'd get HTTP proxy
     *     http://proxy with user myname and password mypass.
     *
     *     One more example: if you specified in command-line --http-proxy-user="myname"
     *     --http-proxy-passwd="mypass" --http-proxy="http://user:pass@proxy", then you'd
     *     get HTTP proxy http://proxy with user user and password pass.
     */
    @JsonProperty("all-proxy")
    val allProxy: String? = null,

    /**
     *  --all-proxy-passwd=<PASSWD>
     * Set password for --all-proxy option.
     */
    @JsonProperty("all-proxy-passwd")
    val allProxyPasswd: String? = null,

    /**
     * --all-proxy-user=<USER>
     * Set user for --all-proxy option.
     */
    @JsonProperty("all-proxy-user")
    val allProxyUser: String? = null,

    /**
     * Set checksum. TYPE is hash type. The supported hash type is listed in Hash Algorithms in aria2c -v.
     * DIGEST is hex digest.
     * For example, setting sha-1 digest looks like this:
     * sha-1=0192ba11326fe2298c8cb4de616f4d4140213838
     * This option applies only to HTTP(S)/FTP downloads.
     *
     * TIP, use CheckSumType.build()
     */
    @JsonProperty("checksum")
    val checksum: String? = null,

    /**
     *  --connect-timeout=<SEC>
     *
     * Set the connect timeout in seconds to establish connection to HTTP/FTP/proxy server.
     * After the connection is established, this option makes no effect and --timeout option is used instead.
     * Default: 60
     */
    @JsonProperty("connect-timeout")
    val connectTimeoutSec: Int? = null,

    /**
     * --dry-run [true|false]
     *
     * If true is given, aria2 just checks whether the remote file is available and doesn't download data.
     * This option has effect on HTTP/FTP download.
     * BitTorrent downloads are canceled if true is specified.
     *
     * Default: false
     */
    @JsonProperty("dry-run")
    val dryRun: Boolean? = null,

    /**
     * --lowest-speed-limit=<SPEED>
     *
     * Close connection if download speed is lower than or equal to this value(bytes per sec).
     * 0 means aria2 does not have a lowest speed limit.
     * You can append K or M (1K = 1024, 1M = 1024K). This option does not affect BitTorrent downloads.
     * Default: 0
     */
    @JsonProperty("lowest-speed-limit")
    val lowestSpeedLimitBytes: Long? = null,


    /**
     * -x, --max-connection-per-server=<NUM>
     *
     * The maximum number of connections to one server for each download.
     * Default: 1
     */
    @JsonProperty("max-connection-per-server")
    val maxConnectionPerServer: Int? = null,

    /**
     * --max-file-not-found=<NUM>
     *
     * If aria2 receives "file not found" status from the remote HTTP/FTP servers
     * NUM times without getting a single byte, then force the download to fail.
     * Specify 0 to disable this option.
     * This options is effective only when using HTTP/FTP servers.
     * The number of retry attempt is counted toward --max-tries, so it should be configured too.
     *
     * Default: 0
     */
    @JsonProperty("max-file-not-found")
    val maxFileNotFound: Int? = null,

    /**
     * -m, --max-tries=<N>
     *
     * Set number of tries. 0 means unlimited. See also --retry-wait. Default: 5
     *
     */
    @JsonProperty("max-tries")
    val maxTries: Int? = null,


    /**
     * -k, --min-split-size=<SIZE>
     *
     * aria2 does not split less than 2*SIZE byte range.
     * For example, let's consider downloading 20MiB file. If SIZE is 10M,
     * aria2 can split file into 2 range [0-10MiB) and [10MiB-20MiB) and download it using 2
     * sources(if --split >= 2, of course). If SIZE is 15M, since 2*15M > 20MiB,
     * aria2 does not split file and download it using 1 source. You can append K or M (1K = 1024, 1M = 1024K).
     *
     * Possible Values: 1M -1024M Default: 20M
     */
    @JsonProperty("min-split-size")
    val minSplitSize: Long? = null,

    /**
     * --netrc-path=<FILE>
     *
     *  Specify the path to the netrc file. Default: $(HOME)/.netrc
     *
     *  Note
     *
     *  Permission of the .netrc file must be 600. Otherwise, the file will be ignored.
     */
    @JsonProperty("netrc-path")
    val netrcPath: String? = null,

    /**
     * -n, --no-netrc [true|false]
     *
     * Disables netrc support. netrc support is enabled by default.
     *
     * Note
     *
     * netrc file is only read at the startup if --no-netrc is false. So if --no-netrc is true at
     * the startup, no netrc is available throughout the session.
     * You cannot get netrc enabled even if you send --no-netrc=false using aria2.changeGlobalOption().
     */
    @JsonProperty("no-netrc")
    val noNetrc: Boolean? = null,

    /**
     * --no-proxy=<DOMAINS>
     *
     * Specify a comma separated list of host names, domains and network addresses with or without a
     * subnet mask where no proxy should be used.
     *
     * Note
     *
     * For network addresses with a subnet mask, both IPv4 and IPv6 addresses work.
     * The current implementation does not resolve the host name in an URI to compare network addresses
     * specified in --no-proxy. So it is only effective if URI has numeric IP addresses.
     */
    @JsonProperty("_no-proxy")
    val noProxy: List<String> = emptyList(),

    /** --out,
     * The file name of the downloaded file. It is always relative to the directory
     * given in --dir option. When the --force-sequential option is used, this option is ignored.*/
    @JsonProperty("out")
    val out: String? = null,

    /**
     * --proxy-method=<METHOD>
     *
     * Set the method to use in proxy request.
     * METHOD is either get or tunnel. HTTPS downloads always use tunnel regardless
     * of this option.
     *
     * Default: get
     */
    @JsonProperty("proxy-method")
    val proxyMethod: ProxyMethod? = null,


    /**
     * -R, --remote-time [true|false]
     *
     * Retrieve timestamp of the remote file from the remote HTTP/FTP server and if it is available,
     * apply it to the local file.
     *
     * Default: false
     *
     */
    @JsonProperty("remote-time")
    val remoteTime: Boolean? = null,

    /**
     *
     * --reuse-uri [true|false]
     *
     * Reuse already used URIs if no unused URIs are left.
     *
     * Default: true
     */
    @JsonProperty("reuse-uri")
    val reuseUri: Boolean? = null,

    /**
     * --retry-wait=<SEC>
     *
     * Set the seconds to wait between retries.
     * When SEC > 0, aria2 will retry downloads when the HTTP server returns a 503 response.
     * Default: 0
     */
    @JsonProperty("retry-wait")
    val retryWaitSec: Int? = null,

    /**
     * --server-stat-of=<FILE>
     *
     * Specify the file name to which performance profile of the servers is saved.
     * You can load saved data using --server-stat-if option. See Server Performance
     * Profile subsection below for file format.
     */
    @JsonProperty("server-stat-of")
    val serverStatOf: String? = null,

    /**
     * --server-stat-if=<FILE>¶
     *
     * Specify the file name to load performance profile of the servers.
     * The loaded data will be used in some URI selector such as feedback.
     * See also --uri-selector option. See Server Performance Profile subsection below for
     * file format.
     */
    @JsonProperty("server-stat-if")
    val serverStatIf: String? = null,

    /**
     * --server-stat-timeout=<SEC>¶
     *
     * Specifies timeout in seconds to invalidate performance profile of the servers
     * since the last contact to them. Default: 86400 (24hours)
     */
    @JsonProperty("server-stat-timeout")
    val serverStatTimeout: Int? = null,

    /**
     * -s, --split=<N>
     *
     * Download a file using N connections. If more than N URIs are given, first N URIs are used
     * and remaining URIs are used for backup. If less than N URIs are given, those URIs are used
     * more than once so that N connections total are made simultaneously. The number of connections
     * to the same host is restricted by the --max-connection-per-server option. See also the
     * --min-split-size option. Default: 5
     *
     * Note
     *
     * Some Metalinks regulate the number of servers to connect.
     * aria2 strictly respects them. This means that if Metalink defines the maxconnections
     * attribute lower than N, then aria2 uses the value of this lower value instead of N.
     *
     */
    @JsonProperty("split")
    val split: Int? = null,

    /**
     * --stream-piece-selector=<SELECTOR>
     * Specify piece selection algorithm used in HTTP/FTP download.
     * Piece means fixed length segment which is downloaded in parallel in segmented download.
     *
     * Default: default
     */
    @JsonProperty("stream-piece-selector")
    val streamPieceSelector: StreamPieceSelector? = null,

    /**
     * -t, --timeout=<SEC>
     *
     * Set timeout in seconds.
     *
     * Default: 60
     */
    @JsonProperty("timeout")
    val timeoutSec: Int? = null,

    /**
     *  --uri-selector=<SELECTOR>
     * Specify URI selection algorithm. The possible values are inorder, feedback and adaptive
     *
     * Default: feedback
     */
    @JsonProperty("uri-selector")
    val uriSelector: UriSelector? = null,

    /**
     *
     * --ca-certificate=<FILE>¶
     *
     * Use the certificate authorities in FILE to verify the peers.
     * The certificate file must be in PEM format and can contain multiple CA certificates.
     * Use --check-certificate option to enable verification.
     *
     * Note
     *
     * If you build with OpenSSL or the recent version of GnuTLS which has
     * gnutls_certificate_set_x509_system_trust() function and the library is properly
     * configured to locate the system-wide CA certificates store, aria2
     * will automatically load those certificates at the startup.
     */
    @JsonProperty("ca-certificate")
    val caCertificate: String? = null,

    /**
     *  --certificate=<FILE>
     *
     * Use the client certificate in FILE. The certificate must be either in PKCS12 (.p12, .pfx) or in PEM format.
     *
     * PKCS12 files must contain the certificate, a key and optionally a chain of additional certificates.
     * Only PKCS12 files with a blank import password can be opened!
     *
     * When using PEM, you have to specify the private key via --private-key as well.
     */
    @JsonProperty("certificate")
    val certificate: String? = null,

    /**
     * --check-certificate [true|false]
     *
     * Verify the peer using certificates specified in --ca-certificate option.
     * Default: true
     */
    @JsonProperty("check-certificate")
    val checkCertificate: Boolean? = null,

    /**
     * --http-accept-gzip [true|false]
     *
     * Send Accept: deflate, gzip request header and inflate response if remote server responds with
     * Content-Encoding: gzip or Content-Encoding: deflate.
     *
     * Default: false
     *
     * Note
     *
     * Some server responds with Content-Encoding: gzip for files which itself is gzipped file.
     * aria2 inflates them anyway because of the response header.
     */
    @JsonProperty("http-accept-gzip")
    val httpAcceptGzip: Boolean? = null,

    /**
     * --http-auth-challenge [true|false]¶
     *
     * Send HTTP authorization header only when it is requested by the server.
     * If false is set, then authorization header is always sent to the server.
     * There is an exception: if user name and password are embedded in URI,
     * authorization header is always sent to the server regardless of this option.
     *
     * Default: false
     */
    @JsonProperty("http-auth-challenge")
    val httpAuthChallenge: Boolean? = null,

    /**
     * --http-no-cache [true|false]
     *
     * Send Cache-Control: no-cache and Pragma: no-cache header to avoid cached content.
     * If false is given, these headers are not sent and you can add Cache-Control header
     * with a directive you like using --header option.
     *
     * Default: false
     */
    @JsonProperty("http-no-cache")
    val httpNoCache: Boolean? = null,

    /**
     * --http-user=<USER>
     *
     * Set HTTP user. This affects all URIs.
     */
    @JsonProperty("http-user")
    val httpUser: String? = null,

    /**
     * --http-passwd=<PASSWD>
     *
     * Set HTTP password. This affects all URIs.
     */
    @JsonProperty("http-passwd")
    val httpPasswd: String? = null,

    /**
     * --http-proxy=<PROXY>
     *
     * Use a proxy server for HTTP. To override a previously defined proxy,
     * use "". See also the --all-proxy option. This affects all http downloads.
     * The format of PROXY is [http://][USER:PASSWORD@]HOST[:PORT]
     */
    @JsonProperty("http-proxy")
    val httpProxy: String? = null,

    /**
     * --http-proxy-passwd=<PASSWD>
     *
     * Set password for --http-proxy.
     */
    @JsonProperty("http-proxy-passwd")
    val httpProxyPasswd: String? = null,

    /**
     * --http-proxy-user=<USER>
     *
     * Set user for --http-proxy.
     */
    @JsonProperty("httpProxyUser")
    val httpProxyUser: String? = null,

    /**
     * --https-proxy=<PROXY>
     *
     * Use a proxy server for HTTPS.
     * To override a previously defined proxy, use "". See also the --all-proxy option.
     * This affects all https download.
     * The format of PROXY is [http://][USER:PASSWORD@]HOST[:PORT]
     */
    @JsonProperty("https-proxy")
    val httpsProxy: String? = null,

    /**
     *  --https-proxy-passwd=<PASSWD>
     *
     * Set password for --https-proxy.
     */
    @JsonProperty("https-proxy-passwd")
    val httpsProxyPasswd: String? = null,

    /**
     *
     * --https-proxy-user=<USER>¶
     *
     * Set user for --https-proxy.
     */
    @JsonProperty("https-proxy-user")
    val httpsProxyUser: String? = null,

    /**
     * --private-key=<FILE>
     *
     * Use the private key in FILE. The private key must be decrypted and in PEM format.
     * The behavior when encrypted one is given is undefined. See also --certificate option.
     */
    @JsonProperty("private-key")
    val privateKey: String? = null,

    /** --referer, Set an http referrer (Referer). This affects all http/https downloads. If * is given,
     * the download URI is also used as the referrer. This may be useful when used together with the
     * --parameterized-uri option.*/
    @JsonProperty("referer")
    val referer: String? = null,

    /**
     * --enable-http-keep-alive [true|false]
     *
     * Enable HTTP/1.1 persistent connection. Default: true
     */
    @JsonProperty("enable-http-keep-alive")
    val enableHttpKeepAlive: Boolean? = null,

    /**
     * --enable-http-pipelining [true|false]
     *
     * Enable HTTP/1.1 pipelining.
     *
     * Default: false
     *
     * Note
     * In performance perspective, there is usually no advantage to enable this option.
     */
    @JsonProperty("enable-http-pipelining")
    val enableHttpPipelining: Boolean? = null,

    /** --header, Append HEADER to HTTP request header. */
    @JsonProperty("_headers")
    val headers: Map<String, String> = emptyMap(),

    /**
     *
     * --load-cookies=<FILE>
     *
     *  Load Cookies from FILE using the Firefox3 format (SQLite3), Chromium/Google Chrome
     *  (SQLite3) and the Mozilla/Firefox(1.x/2.x)/Netscape format.
     *
     * Note
     * If aria2 is built without libsqlite3, then it doesn't support Firefox3 and
     * Chromium/Google Chrome cookie format.
     */
    @JsonProperty("load-cookies")
    val loadCookies: String? = null,

    /**
     * --save-cookies=<FILE>¶
     *
     * Save Cookies to FILE in Mozilla/Firefox(1.x/2.x)/ Netscape format.
     * If FILE already exists, it is overwritten. Session Cookies are also saved and their
     * expiry values are treated as 0. Possible Values: /path/to/file
     */
    @JsonProperty("save-cookies")
    val saveCookies: String? = null,

    /**
     * --use-head [true|false]
     *
     * Use HEAD method for the first request to the HTTP server.
     * Default: false
     */
    @JsonProperty("use-head")
    val useHead: Boolean? = null,

    /**
     * --user-agent,
     * Set user agent for HTTP(S) downloads.
     * Default: aria2/$VERSION, $VERSION is replaced by package version.
     * */
    @JsonProperty("user-agent")
    val userAgent: String? = null,

    /**
     * --ftp-user=<USER>
     *
     * Set FTP user. This affects all URIs. Default: anonymous
     */
    @JsonProperty("ftp-user")
    val ftpUser: String? = null,

    /**
     *
     * --ftp-passwd=<PASSWD>
     *
     * Set FTP password. This affects all URIs.
     * If user name is embedded but password is missing in URI, aria2 tries to resolve password using .netrc.
     * If password is found in .netrc, then use it as password. If not, use the password specified in this option.
     *
     * Default: ARIA2USER@
     */
    @JsonProperty("ftp-passwd")
    val ftpPasswd: String? = null,

    /**
     * -p, --ftp-pasv [true|false]
     *
     * Use the passive mode in FTP. If false is given, the active mode will be used. Default: true
     *
     * Note
     *
     * This option is ignored for SFTP transfer.
     */
    @JsonProperty("ftp-pasv")
    val ftpPasv: Boolean? = null,

    /**
     * --ftp-proxy-passwd=<PASSWD>
     *
     * Set password for --ftp-proxy option.
     */
    @JsonProperty("ftp-proxy-passwd")
    val ftpProxyPasswd: String? = null,

    /**
     * --ftp-proxy-user=<USER>
     *
     * Set user for --ftp-proxy option.
     */
    @JsonProperty("ftp-proxy-user")
    val ftpProxyUser: String? = null,

    /**
     * --ftp-type=<TYPE>
     *
     * Set FTP transfer type. TYPE is either binary or ascii. Default: binary
     *
     * Note
     *
     * This option is ignored for SFTP transfer.
     */
    @JsonProperty("ftp-type")
    val ftpType: FtpType? = null,

    /**
     * --ftp-reuse-connection [true|false]
     *
     * Reuse connection in FTP.
     *
     * Default: true
     */
    @JsonProperty("ftp-reuse-connection")
    val ftpReuseConnection: Boolean? = null,

    /**
     * --ssh-host-key-md=<TYPE>=<DIGEST>¶
     *
     * Set checksum for SSH host public key.
     * TYPE is hash type. The supported hash type is sha-1 or md5.
     * DIGEST is hex digest. For example: sha-1=b030503d4de4539dc7885e6f0f5e256704edf4c3.
     * This option can be used to validate server's public key when SFTP is used.
     * If this option is not set, which is default, no validation takes place.
     */
    @JsonProperty("ssh-host-key-md")
    val sshHostKeyMd: String? = null,

    /**
     *
     * --select-file=<INDEX>...
     *
     * Set file to download by specifying its index. You can find the file index using the
     * --show-files option. Multiple indexes can be specified by using ,, for example: 3,6.
     * You can also use - to specify a range: 1-5. , and - can be used together: 1-5,8,9.
     * When used with the -M option, index may vary depending on the query
     * (see --metalink-* options).
     *
     * Note
     *
     * In multi file torrent, the adjacent files specified by this option may also be
     * downloaded. This is by design, not a bug. A single piece may include several
     * files or part of files, and aria2 writes the piece to the appropriate files.
     */
    @JsonProperty("select-file")
    val selectFile: String? = null,

    /**
     * --bt-detach-seed-only [true|false]
     *
     * Exclude seed only downloads when counting concurrent active downloads (See -j option).
     * This means that if -j3 is given and this option is turned on and 3 downloads are active and one
     * of those enters seed mode, then it is excluded from active download count (thus it becomes 2),
     * and the next download waiting in queue gets started. But be aware that seeding item is still recognized
     * as active download in RPC method.
     *
     * Default: false
     */
    @JsonProperty("bt-detach-seed-only")
    val btDetachSeedOnly: Boolean? = null,

    /**
     * --bt-enable-hook-after-hash-check [true|false]
     *
     * Allow hook command invocation after hash check (see -V option) in BitTorrent download.
     * By default, when hash check succeeds, the command given by --on-bt-download-complete is executed.
     * To disable this action, give false to this option. Default: true
     */
    @JsonProperty("bt-enable-hook-after-hash-check")
    val btEnableHookAfterHashCheck: Boolean? = null,

    /**
     *  --bt-exclude-tracker=<URI>[,...]
     *
     * Comma separated list of BitTorrent tracker's announce URI to remove.
     * You can use special value * which matches all URIs, thus removes all announce URIs.
     * When specifying * in shell command-line, don't forget to escape or quote it.
     * See also --bt-tracker option.
     */
    @JsonProperty("_bt-exclude-tracker")
    val btExcludeTracker: List<String> = emptyList(),


    /**
     * --bt-external-ip=<IPADDRESS>
     *
     * Specify the external IP address to use in BitTorrent download and DHT.
     * It may be sent to BitTorrent tracker. For DHT, this option should be set to report that
     * local node is downloading a particular torrent. This is critical to use DHT in a private network.
     * Although this function is named external, it can accept any kind of IP addresses.
     */
    @JsonProperty("bt-external-ip")
    val btExternalIp: String? = null,

    /**
     *  --bt-force-encryption [true|false]
     *
     * Requires BitTorrent message payload encryption with arc4.
     * This is a shorthand of --bt-require-crypto --bt-min-crypto-level=arc4.
     * This option does not change the option value of those options. If true is given,
     * deny legacy BitTorrent handshake and only use Obfuscation handshake and always encrypt message payload.
     * Default: false
     */
    @JsonProperty("bt-force-encryption")
    val btForceEncryption: Boolean? = null,


    /**
     *  --bt-hash-check-seed [true|false]
     *
     * If true is given, after hash check using --check-integrity option and file is complete,
     * continue to seed file. If you want to check file and download it only when it is damaged or incomplete,
     * set this option to false. This option has effect only on BitTorrent download.
     * Default: true
     */
    @JsonProperty("bt-hash-check-seed")
    val btHashCheckSeed: Boolean? = null,

    /**
     * --bt-load-saved-metadata [true|false]
     *
     * Before getting torrent metadata from DHT when downloading with magnet link, first try to read
     * file saved by --bt-save-metadata option. If it is successful, then skip downloading metadata from DHT.
     *
     * Default: false
     */
    @JsonProperty("bt-load-saved-metadata")
    val btLoadSavedMetadata: Boolean? = null,

    /**
     * --bt-max-open-files=<NUM>
     *
     * Specify maximum number of files to open in multi-file BitTorrent/Metalink download globally.
     *
     * Default: 100
     */
    @JsonProperty("bt-max-open-files")
    val btMaxOpenFiles: Int? = null,

    /**
     * --bt-max-peers=<NUM>
     *
     * Specify the maximum number of peers per torrent.
     * 0 means unlimited.
     * See also --bt-request-peer-speed-limit option.
     * Default: 55
     */
    @JsonProperty("bt-max-peers")
    val btMaxPeers: Int? = null,

    /**
     * Download meta data only. The file(s) described in meta data will not be downloaded.
     * This option has effect only when BitTorrent Magnet URI is used.
     * See also --bt-save-metadata option.
     *
     * Default: false
     */
    @JsonProperty("bt-metadata-only")
    val btMetadataOnly: Boolean? = null,

    /**
     * --bt-min-crypto-level=plain|arc4¶
     *
     * Set minimum level of encryption method.
     * If several encryption methods are provided by a peer, aria2 chooses the lowest one
     * which satisfies the given level.
     *
     * Default: plain
     */
    @JsonProperty("bt-min-crypto-level")
    val btMinCryptoLevel: String? = null,

    /**
     * --bt-prioritize-piece=head[=<SIZE>],tail[=<SIZE>]¶
     *
     * Try to download first and last pieces of each file first.
     * This is useful for previewing files.
     * The argument can contain 2 keywords: head and tail.
     * To include both keywords, they must be separated by comma.
     * These keywords can take one parameter, SIZE. For example,
     * if head=<SIZE> is specified, pieces in the range of first SIZE bytes of each
     * file get higher priority. tail=<SIZE> means the range of last SIZE bytes of each file.
     * SIZE can include K or M (1K = 1024, 1M = 1024K). If SIZE is omitted, SIZE=1M is used.
     */
    @JsonProperty("bt-prioritize-piece")
    val btPrioritizePiece: String? = null,

    /**
     * --bt-remove-unselected-file [true|false]
     *
     * Removes the unselected files when download is completed in BitTorrent.
     * To select files, use --select-file option. If it is not used,
     * all files are assumed to be selected.
     * Please use this option with care because it will actually remove files from your disk.
     *
     * Default: false
     */
    @JsonProperty("bt-remove-unselected-file")
    val btRemoveUnselectedFile: Boolean? = null,

    /**
     * --bt-require-crypto [true|false]
     *
     * If true is given, aria2 doesn't accept and establish connection with legacy BitTorrent
     * handshake(\19BitTorrent protocol). Thus aria2 always uses Obfuscation handshake.
     *
     * Default: false
     */
    @JsonProperty("bt-require-crypto")
    val btRequireCrypto: Boolean? = null,

    /**
     * --bt-request-peer-speed-limit=<SPEED>
     * If the whole download speed of every torrent is lower than SPEED,
     * aria2 temporarily increases the number of peers to try for more download speed.
     * Configuring this option with your preferred download speed can increase your download speed in some cases.
     * You can append K or M (1K = 1024, 1M = 1024K).
     * Default: 50K
     */
    @JsonProperty("bt-request-peer-speed-limit")
    val btRequestPeerSpeedLimitBytes: Long? = null,

    /**
     * Save meta data as ".torrent" file.
     * This option has effect only when BitTorrent Magnet URI is used.
     * The file name is hex encoded info hash with suffix ".torrent".
     * The directory to be saved is the same directory where download file is saved.
     * If the same file already exists, meta data is not saved. See also --bt-metadata-only
     * option.
     *
     * Default: false
     */
    @JsonProperty("bt-save-metadata")
    val btSaveMetadata: Boolean? = null,

    /**
     *  --bt-seed-unverified [true|false]
     *  Seed previously downloaded files without verifying piece hashes.
     *  Default: false
     */
    @JsonProperty("bt-seed-unverified")
    val btSeedUnverified: Boolean? = null,
    /**
     * --bt-stop-timeout=<SEC>
     *
     * Stop BitTorrent download if download speed is 0 in consecutive SEC seconds.
     * If 0 is given, this feature is disabled.
     *
     * Default: 0
     */
    @JsonProperty("bt-stop-timeout")
    val btStopTimeoutSec: Int? = null,

    /**
     *  --bt-tracker=<URI>[,...]
     *
     * Comma separated list of additional BitTorrent tracker's announce URI.
     * These URIs are not affected by --bt-exclude-tracker option because they are added after URIs in
     * --bt-exclude-tracker option are removed.
     *
     */
    @JsonProperty("_bt-tracker")
    val btTracker: List<String> = emptyList(),

    /**
     *  --bt-tracker-connect-timeout=<SEC>
     *
     * Set the connect timeout in seconds to establish connection to tracker.
     * After the connection is established, this option makes no effect and
     * --bt-tracker-timeout option is used instead.
     *
     * Default: 60
     */
    @JsonProperty("bt-tracker-connect-timeout")
    val btTrackerConnectTimeoutSec: Int? = null,

    /**
     * --bt-tracker-interval=<SEC>
     *
     * Set the interval in seconds between tracker requests.
     * This completely overrides interval value and aria2 just uses this value and ignores the min
     * interval and interval value in the response of tracker. If 0 is set, aria2 determines interval
     * based on the response of tracker and the download progress.
     *
     * Default: 0
     */
    @JsonProperty("bt-tracker-interval")
    val btTrackerIntervalSec: Int? = null,

    /**
     * --bt-tracker-timeout=<SEC>
     *
     *  Set timeout in seconds. Default: 60
     */
    @JsonProperty("bt-tracker-timeout")
    val btTrackerTimeoutSec: Int? = null,

    /**
     * --dht-entry-point=<HOST>:<PORT>
     *
     * Set host and port as an entry point to IPv4 DHT network.
     */
    @JsonProperty("dht-entry-point")
    val dhtEntryPoint: String? = null,

    /**
     * --dht-entry-point=<HOST>:<PORT>
     *
     * Set host and port as an entry point to IPv6 DHT network.
     */
    @JsonProperty("dht-entry-point6")
    val dhtEntryPoint6: String? = null,

    /**
     * --dht-file-path=<PATH>
     *
     * Change the IPv4 DHT routing table file to PATH. Default: $HOME/.aria2/dht.dat
     * if present, otherwise $XDG_CACHE_HOME/aria2/dht.dat.
     */
    @JsonProperty("dht-file-path")
    val dhtFilePath: String? = null,

    /**
     * --dht-file-path6=<PATH>
     *
     * Change the IPv6 DHT routing table file to PATH. Default: $HOME/.aria2/dht6.dat
     * if present, otherwise $XDG_CACHE_HOME/aria2/dht6.dat.
     */
    @JsonProperty("dht-file-path6")
    val dhtFilePath6: String? = null,

    /**
     * --dht-listen-addr6=<ADDR>
     *
     * Specify address to bind socket for IPv6 DHT.
     * It should be a global unicast IPv6 address of the host.
     */
    @JsonProperty("dht-listen-addr6")
    val dhtListenAddr6: String? = null,

    /**
     *
     * --dht-listen-port=<PORT>...
     *
     * Set UDP listening port used by DHT(IPv4, IPv6) and UDP tracker.
     * Multiple ports can be specified by using ,, for example: 6881,6885.
     * You can also use - to specify a range: 6881-6999. , and - can be used together.
     * Default: 6881-6999
     *
     * Note
     *
     * Make sure that the specified ports are open for incoming UDP traffic.
     *
     */
    @JsonProperty("dht-listen-port")
    val dhtListenPort: String? = null,

    /**
     *
     * --dht-message-timeout=<SEC>
     *
     * Set timeout in seconds.
     *
     * Default: 10
     *
     */
    @JsonProperty("dht-message-timeout")
    val dhtMessageTimeoutSec: Int? = null,

    /**
     *
     * --follow-torrent=true|false|mem¶
     *
     * If true or mem is specified, when a file whose suffix is
     * .torrent or content type is application/x-bittorrent is downloaded,
     * aria2 parses it as a torrent file and downloads files mentioned in it.
     * If mem is specified, a torrent file is not written to the disk,
     * but is just kept in memory. If false is specified,
     * the .torrent file is downloaded to the disk, but is not parsed as a
     * torrent and its contents are not downloaded.
     *
     * Default: true
     *
     */
    @JsonProperty("follow-torrent")
    val followTorrent: Boolean? = null,

    /**
     * --listen-port=<PORT>...
     *
     * Set TCP port number for BitTorrent downloads.
     * Multiple ports can be specified by using ,, for example: 6881,6885.
     * You can also use - to specify a range: 6881-6999. ,
     * and - can be used together: 6881-6889,6999. Default: 6881-6999
     *
     * Note
     *
     * Make sure that the specified ports are open for incoming TCP traffic.
     */
    @JsonProperty("listen-port")
    val listenPort: String? = null,


    /**
     * -u, --max-upload-limit=<SPEED>
     *
     * Set max upload speed per each torrent in bytes/sec.
     * 0 means unrestricted.
     * You can append K or M (1K = 1024, 1M = 1024K).
     * To limit the overall upload speed, use --max-overall-upload-limit option.
     * Default: 0
     */
    @JsonProperty("max-upload-limit")
    val maxUploadLimitBytes: Long? = null,

    /**
     * --peer-id-prefix=<PEER_ID_PREFIX>¶
     *
     * Specify the prefix of peer ID. The peer ID in BitTorrent is 20 byte length.
     * If more than 20 bytes are specified, only first 20 bytes are used.
     * If less than 20 bytes are specified, random byte data are added to make its length 20
     * bytes.
     *
     * Default: A2-$MAJOR-$MINOR-$PATCH-, $MAJOR, $MINOR and $PATCH
     * are replaced by major, minor and patch version number respectively.
     * For instance, aria2 version 1.18.8 has prefix ID A2-1-18-8-.
     */
    @JsonProperty("peer-id-prefix")
    val peerIdPrefix: String? = null,

    /**
     * --peer-agent=<PEER_AGENT>¶
     *
     * Specify the string used during the bitorrent extended handshake for the peer's
     * client version.
     *
     * Default: aria2/$MAJOR.$MINOR.$PATCH, $MAJOR, $MINOR and $PATCH are
     * replaced by major, minor and patch version number respectively.
     * For instance, aria2 version 1.18.8 has peer agent aria2/1.18.8.
     */
    @JsonProperty("peer-agent")
    val peerAgent: String? = null,

    /** --seed-ratio, Specify share ratio. Seed completed torrents until share ratio reaches RATIO.
     * You are strongly encouraged to specify equals or more than 1.0 here.
     * Specify 0.0 if you intend to do seeding regardless of share ratio.
     * If --seed-time option is specified along with this option, seeding ends when at least one
     * of the conditions is satisfied. Default: 1.0
     * */
    @JsonProperty("seed-ratio")
    val seedRatio: Float? = null,

    /** --seed-time,
     * Specify seeding time in (fractional) minutes.
     * Also see the --seed-ratio option.
     * NOTE! Specifying --seed-time=0 disables seeding after download completed.
     * */
    @JsonProperty("seed-time")
    val seedTimeMin: Float? = null,

    /**
     * --bt-piece-selector
     *
     * How torrents should be downloaded,
     * inorder makes it easier to stream them but is worse for general downloading */
    @JsonProperty("bt-piece-selector")
    val btPieceSelector: BtPieceSelector? = null,

    /**
     * -T, --torrent-file=<TORRENT_FILE>
     *
     * The path to the ".torrent" file.
     * You are not required to use this option because you can specify ".torrent" files
     * without --torrent-file.
     */
    @JsonProperty("torrent-file")
    val torrentFile: String? = null,
)

enum class HelpType {
    @JsonProperty("#advanced")
    Advanced,

    @JsonProperty("#http")
    Http,

    @JsonProperty("#https")
    Https,

    @JsonProperty("#ftp")
    Ftp,

    @JsonProperty("#metalink")
    Metalink,

    @JsonProperty("#bittorrent")
    Bittorrent,

    @JsonProperty("#cookie")
    Cookie,

    @JsonProperty("#hook")
    Hook,

    @JsonProperty("#file")
    File,

    @JsonProperty("#rpc")
    Rpc,

    @JsonProperty("#checksum")
    Checksum,

    @JsonProperty("#experimental")
    Experimental,

    @JsonProperty("#deprecated")
    Deprecated,

    @JsonProperty("#help")
    Help,

    @JsonProperty("#all")
    All
}

enum class ProxyMethod {
    @JsonProperty("get")
    Get,

    @JsonProperty("tunnel")
    Tunnel
}

enum class UriSelector {
    /**  If inorder is given, URI is tried in the order appeared in the URI list. */
    @JsonProperty("inorder")
    Inorder,

    /**
    If feedback is given, aria2 uses download speed observed in the previous downloads and choose
    fastest server in the URI list. This also effectively skips dead mirrors.
    The observed download speed is a part of performance profile of servers mentioned in
    --server-stat-of and --server-stat-if options.
     */
    @JsonProperty("feedback")
    Feedback,

    /**
     * If adaptive is given, selects one of the best mirrors for the first and reserved connections.
     * For supplementary ones, it returns mirrors which has not been tested yet, and if each of them
     * has already been tested, returns mirrors which has to be tested again. Otherwise,
     * it doesn't select anymore mirrors. Like feedback, it uses a performance profile of servers.
     */
    @JsonProperty("adaptive")
    Adaptive,
}

enum class StreamPieceSelector {
    /** If default is given, aria2 selects piece so that it reduces the number of establishing connection.
     * This is reasonable default behavior because establishing connection is an expensive operation. */
    @JsonProperty("default")
    Default,

    /**  If inorder is given, aria2 selects piece which has minimum index.
     * Index=0 means first of the file. This will be useful to view movie while downloading it.
     * --enable-http-pipelining option may be useful to reduce re-connection overhead.
     * Please note that aria2 honors --min-split-size option, so it will be necessary to specify a
     * reasonable value to --min-split-size option. */
    @JsonProperty("inorder")
    Inorder,

    /** If random is given, aria2 selects piece randomly. */
    @JsonProperty("random")
    Random,

    /**
     * If geom is given, at the beginning aria2 selects piece which has minimum index like inorder,
     * but it exponentially increasingly keeps space from previously selected piece.
     * This will reduce the number of establishing connection and at the same time it will download
     * the beginning part of the file first. This will be useful to view movie while downloading it.
     */
    @JsonProperty("geom")
    Geom
}

enum class CheckSumType(val typeName: String) {
    Sha1("sha-1"),
    Sha224("sha-224"),
    Sha256("sha-256"),
    Sha384("sha-384"),
    Sha512("sha-512"),
    Md5("md5"),
    Adler32("adler32"),
}

fun CheckSumType.build(checksum: String): String {
    return "${this.typeName}=$checksum"
}

enum class FtpType {
    @JsonProperty("binary")
    Binary,

    @JsonProperty("ascii")
    Ascii
}


/** Docs over at https://aria2.github.io/manual/en/html/aria2c.html */
data class UriRequest(
    /** THIS IS A NON ARIA2 RELATED SETTING set to null if you don't want to track it*/
    @JsonProperty("id")
    val id: Long? = null,

    /** THiS IS ONLY FOR NOTIFICATION, set to null if you don't want any notification from this download*/
    @JsonProperty("notificationMetaData")
    val notificationMetaData: NotificationMetaData? = null,

    /**uris is an array of HTTP/FTP/SFTP/BitTorrent URIs (strings) pointing to the same resource.
     * If you mix URIs pointing to different resources, then the download may fail or be corrupted
     * without aria2 complaining. When adding BitTorrent Magnet URIs, uris must have only one element
     * and it should be BitTorrent Magnet URI. */
    @JsonProperty("uris")
    val uris: List<String>,

    /** All args in aria2c */
    @JsonProperty("args")
    val args: Aria2Args
)

enum class BtPieceSelector {
    @JsonProperty("default")
    Default,

    @JsonProperty("inorder")
    Inorder
}

/** Creates a uri request without using the data class
 *
 * @param uri url to the raw stream or magnet link
 * @param fileName output filename, will not override torrent names, null will default the name based on the url
 * @param directory the output directory, will cause download error if you don't have access to that directory,
 * null defaults to the directory specified when starting aria2c
 * @param headers the request headers
 * @param userAgent defaults to aria2/$VERSION
 * @param seed if false, disables seeding after download completed
 * */
fun newUriRequest(
    id: Long?,
    uri: String,
    fileName: String? = null,
    directory: String? = null,
    headers: Map<String, String> = emptyMap(),
    userAgent: String? = null,
    seed: Boolean = false,
    notificationMetaData: NotificationMetaData? = null,
    stream : Boolean = false,
): UriRequest {
    return UriRequest(
        id = id,
        uris = listOf(uri),
        notificationMetaData = notificationMetaData,
        args = Aria2Args(
            out = fileName,
            headers = headers,
            dir = directory,
            userAgent = userAgent,
            seedTimeMin = if (seed) null else 0.0f,
            //streamPieceSelector = if(stream) StreamPieceSelector.Inorder else null,
            btPieceSelector = if(stream) BtPieceSelector.Inorder else null,
        ),
    )
}

data class SavedData(
    @JsonProperty("uriRequest") val uriRequest: UriRequest,
    @JsonProperty("files") val files: List<AbstractClient.JsonFile>
)

fun getDownloadStatusFromTell(str: String?): DownloadStatusTell? {
    return when (str) {
        "active" -> DownloadStatusTell.Active
        "complete" -> DownloadStatusTell.Complete
        "waiting" -> DownloadStatusTell.Waiting
        "error" -> DownloadStatusTell.Error
        "removed" -> DownloadStatusTell.Removed
        "paused" -> DownloadStatusTell.Paused
        else -> null
    }
}

data class SmallJsonResponse(
    @JsonProperty("id") val id: String,
)

data class JsonIdResponse(
    @JsonProperty("id") val id: String,
    @JsonProperty("error") val error: JsonError?,
    @JsonProperty("result") val resultGid: String,
)

//data class JsonMethodResponse(
//    @JsonProperty("method") val method: String,
//    @JsonProperty("params") val params: ArrayList<Map<String, String>> = arrayListOf(),
//)

data class JsonError(
    @JsonProperty("code") val code: Int,
    @JsonProperty("message") val message: String,
)

data class Metadata(
    @JsonProperty("items")
    val items: ArrayList<AbstractClient.JsonTell>
) {
    val status by lazy { getStatus(items) }
    val totalLength by lazy { items.sumOf { it.totalLength } }
    val downloadedLength by lazy { items.sumOf { it.completedLength } }
    val progressPercentage by lazy { ((downloadedLength * 100L + 1L) / (totalLength + 1L)).toInt() }
    val downloadSpeed by lazy { items.sumOf { it.downloadSpeed } }
}

fun getStatus(status: ArrayList<AbstractClient.JsonTell>): DownloadStatusTell? {
    val statusList = Array(status.size) { i ->
        getDownloadStatusFromTell(status[i].status)
    }

    return when { // this is the priority sorter based on all the files
        statusList.contains(DownloadStatusTell.Active) -> DownloadStatusTell.Active
        statusList.contains(DownloadStatusTell.Waiting) -> DownloadStatusTell.Waiting
        statusList.contains(DownloadStatusTell.Error) -> DownloadStatusTell.Error
        statusList.contains(DownloadStatusTell.Paused) -> DownloadStatusTell.Paused
        statusList.contains(DownloadStatusTell.Removed) -> DownloadStatusTell.Removed
        statusList.contains(DownloadStatusTell.Complete) -> DownloadStatusTell.Complete
        else -> null
    }
}