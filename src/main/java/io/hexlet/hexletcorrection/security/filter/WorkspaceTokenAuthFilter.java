package io.hexlet.hexletcorrection.security.filter;

//@RequiredArgsConstructor
public class WorkspaceTokenAuthFilter /*extends OncePerRequestFilter*/ {
//
//    private final AuthenticationManager manager;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
//        final var name = req.getHeader("Workspace-Name");
//        final var token = req.getHeader("Workspace-Token");
//
//        if (nonNull(name) && nonNull(token)) {
//            final var authentication = new WorkspaceTokenAuthentication(name, token);
//            manager.authenticate(authentication);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            doFilter(req, res, filterChain);
//        }
//    }
//
//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        return !"POST".equals(request.getMethod()) && !API_TYPOS.equals(request.getServletPath());
//    }
}
