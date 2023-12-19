function newton(f, dfdx, x1)
    # Operating parameters.
    funtol = 100 * eps()
    xtol = 100 * eps()
    maxiter = 40

    x = [x1]
    y = f(x1)
    dx = Inf   # for initial pass below
    k = 1

    while (abs(dx) > xtol) && (abs(y) > funtol) && (k < maxiter)
        dydx = dfdx(x[k])
        dx = -y / dydx            # Newton step
        push!(x, x[k] + dx)        # append new estimate
        k = k + 1
        y = f(x[k])
    end

    if k == maxiter
        @warn "Maximum number of iterations reached."
    end

    return x
end

d = x -> x^5+1
ddx = x -> 5x^4
plt = plot([d],-10,10,l=3,label=["\$y=f(x)\$" "\$y=x\$"],aspect_ratio=1,
    title="Q4",legend=:bottomright)

display(plt)

newton(d,ddx,5.0)